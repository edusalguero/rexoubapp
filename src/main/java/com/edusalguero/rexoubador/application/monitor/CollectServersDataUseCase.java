package com.edusalguero.rexoubador.application.monitor;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.ServerRepository;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.service.ServerMonitorsExecutorService;
import com.edusalguero.rexoubador.domain.service.executor.ExecutionException;
import com.edusalguero.rexoubador.domain.service.executor.command.response.CommandResponse;
import com.edusalguero.rexoubador.domain.service.executor.command.response.HarvestCommandResponse;
import com.edusalguero.rexoubador.domain.service.executor.command.response.ObserverCommandResponse;
import com.edusalguero.rexoubador.domain.service.executor.command.response.UptimeCommandResponse;
import com.edusalguero.rexoubador.domain.shared.CheckStatus;
import com.edusalguero.rexoubador.domain.shared.HarvestStatus;
import com.edusalguero.rexoubador.domain.shared.MachineStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class CollectServersDataUseCase {

    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private ServerMonitorsExecutorService serverMonitorsExecutorService;

    public void execute() {
        Collection<Server> servers = serverRepository.toBeCollected();

        for (Server server : servers) {
            server.updateHarvestStatus(HarvestStatus.IN_PROGRESS);
            try {
                Collection<CommandResponse> collectedData = serverMonitorsExecutorService.collect(server);
                saveHarvestedData(server, collectedData);
                server.updateHarvestStatus(HarvestStatus.DONE);
                server.updateHarvestDate(new Date());
                server.updateMachineStatus(MachineStatus.UP);
            } catch (ExecutionException e) {
                server.updateHarvestStatus(HarvestStatus.CONNECTION_ERROR);
                server.updateMachineStatus(MachineStatus.DOWN);
            }
            serverRepository.update(server);

        }
    }

    private void saveHarvestedData(Server server, Collection<CommandResponse> collectedData) {

        Date now = new Date();

        CollectServerResponse collectServerResponse = new CollectServerResponse(now, server.user(), server);
        for (CommandResponse result : collectedData) {
            if (result instanceof HarvestCommandResponse) {
                ServerHarvesterId id = (ServerHarvesterId) ((HarvestCommandResponse) result).getId();
                collectServerResponse.addMetric(id, (HarvesterType) result.getName(), result.getData());
                server.harvester(id)
                        .addCollectedData(now, result.toJson());
            } else if (result instanceof ObserverCommandResponse) {
                ServerObserverId id = (ServerObserverId) ((ObserverCommandResponse) result).getId();
                CheckStatus checkStatus = (CheckStatus) result.getData("status");
                collectServerResponse.addService(id, (String) result.getName(), checkStatus);
                server.observer(id)
                        .addObservation(now, checkStatus);
            } else if (result instanceof UptimeCommandResponse) {
                Integer uptime = (Integer) result.getData("uptime");
                server.updateUptime(uptime);
                collectServerResponse.setUptime(uptime);
            }
        }
        System.out.println(collectServerResponse.toJson());
    }
}
