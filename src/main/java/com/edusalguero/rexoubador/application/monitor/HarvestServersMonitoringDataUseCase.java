package com.edusalguero.rexoubador.application.monitor;

import com.edusalguero.rexoubador.domain.model.monitor.Report;
import com.edusalguero.rexoubador.domain.model.monitor.ReportId;
import com.edusalguero.rexoubador.domain.model.monitor.ReportRepository;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.ServerRepository;
import com.edusalguero.rexoubador.domain.service.ServerMonitorsExecutorService;
import com.edusalguero.rexoubador.domain.service.executor.ExecutionException;
import com.edusalguero.rexoubador.domain.service.executor.command.response.CommandResponseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HarvestServersMonitoringDataUseCase {

    private static final Logger logger = LoggerFactory.getLogger(HarvestServersMonitoringDataUseCase.class);

    private final ServerRepository serverRepository;

    private final ReportRepository reportRepository;

    private final ServerMonitorsExecutorService serverMonitorsExecutorService;

    @Autowired
    public HarvestServersMonitoringDataUseCase(ServerRepository serverRepository, ReportRepository reportRepository, ServerMonitorsExecutorService serverMonitorsExecutorService) {
        this.serverRepository = serverRepository;
        this.reportRepository = reportRepository;
        this.serverMonitorsExecutorService = serverMonitorsExecutorService;
    }

    public void execute() {
        logger.info("Harvest start!");
        logger.info("Retrieving collectible servers");
        Collection<Server> servers = serverRepository.toBeCollected();
        Integer count = servers.size();
        logger.info(String.format("There are [%s] collectable servers", count));
        Integer i = 1;
        for (Server server : servers) {
            logger.info(String.format("Collecting monitoring data from server [%s] [%s/%s]...", server.id(), i, count));
            server.monitoringStart();
            try {
                Collection<CommandResponseInterface> collectedData = serverMonitorsExecutorService.collect(server);
                ReportId reportId = reportRepository.nextIdentity();
                Report report = server.packageMonitoredData(reportId, collectedData);
                reportRepository.save(report);
                logger.debug("Monitoring data: " + report.toJson());
            } catch (ExecutionException e) {
                logger.error(String.format("Error collecting data for server [%s]: %s", server.id(), e.getMessage()));
                server.reportMonitorProblem();
            }
            serverRepository.update(server);
            logger.info(String.format("Monitoring data collected for server [%s]", server.id()));
            i++;
        }
        logger.info("Harvest end!");
    }
}
