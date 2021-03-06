package com.edusalguero.rexoubapp.application.server.harvester;

import com.edusalguero.rexoubapp.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterNotFoundException;
import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterRepository;
import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubapp.domain.model.server.ServerRepository;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvesterRepository;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerHarvesterCreateUseCase {

    private final ServerRepository serverRepository;

    private final HarvesterRepository harvesterRepository;

    private final ServerHarvesterRepository serverHarvesterRepository;

    @Autowired
    public ServerHarvesterCreateUseCase(ServerRepository serverRepository, HarvesterRepository harvesterRepository, ServerHarvesterRepository serverHarvesterRepository) {
        this.serverRepository = serverRepository;
        this.harvesterRepository = harvesterRepository;
        this.serverHarvesterRepository = serverHarvesterRepository;
    }

    public ServerHarvesterId execute(UserId userId, ServerId serverId, HarvesterId harvesterId) {
        Server server = serverRepository.ofId(serverId);
        if (!server.user().userId().equals(userId)) {
            throw new ServerNotFoundException();
        }

        Harvester harvester = harvesterRepository.ofId(harvesterId);
        if (!harvester.user().userId().equals(userId)) {
            throw new HarvesterNotFoundException();
        }

        ServerHarvesterId serverHarvesterId = serverHarvesterRepository.nextIdentity();
        server.addHarvester(serverHarvesterId, harvester);
        serverRepository.update(server);
        return serverHarvesterId;
    }
}
