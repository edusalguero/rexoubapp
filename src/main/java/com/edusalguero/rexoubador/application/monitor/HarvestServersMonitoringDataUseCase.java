package com.edusalguero.rexoubador.application.monitor;

import com.edusalguero.rexoubador.domain.model.monitor.Report;
import com.edusalguero.rexoubador.domain.model.monitor.ReportId;
import com.edusalguero.rexoubador.domain.model.monitor.ReportRepository;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.ServerRepository;
import com.edusalguero.rexoubador.domain.service.ServerMonitorsExecutorService;
import com.edusalguero.rexoubador.domain.service.executor.ExecutionException;
import com.edusalguero.rexoubador.domain.service.executor.command.response.CommandResponseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HarvestServersMonitoringDataUseCase {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ServerMonitorsExecutorService serverMonitorsExecutorService;

    public void execute() {
        Collection<Server> servers = serverRepository.toBeCollected();

        for (Server server : servers) {
            server.monitoringStart();
            try {
                Collection<CommandResponseInterface> collectedData = serverMonitorsExecutorService.collect(server);
                ReportId reportId = reportRepository.nextIdentity();
                Report report = server.packageMonitoredData(reportId, collectedData);
                reportRepository.save(report);
                System.out.println(reportRepository.ofId(reportId).toJson());
            } catch (ExecutionException e) {
                server.reportMonitorProblem();
            }
            serverRepository.update(server);
        }
    }
}
