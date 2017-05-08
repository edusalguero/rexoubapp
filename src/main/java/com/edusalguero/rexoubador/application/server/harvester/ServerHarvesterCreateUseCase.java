package com.edusalguero.rexoubador.application.server.harvester;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterNotFoundException;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.HarvesterRepositoryJPA;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ServerHarvesterRepositoryJPA;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ServerRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerHarvesterCreateUseCase {

    @Autowired
    private ServerRepositoryJPA serverRepository;

    @Autowired
    private HarvesterRepositoryJPA harvesterRepository;

    @Autowired
    private ServerHarvesterRepositoryJPA serverHarvesterRepository;

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
