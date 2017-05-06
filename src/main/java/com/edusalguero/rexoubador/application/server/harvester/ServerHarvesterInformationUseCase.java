package com.edusalguero.rexoubador.application.server.harvester;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.ServerId;
import com.edusalguero.rexoubador.domain.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ServerRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerHarvesterInformationUseCase {
    @Autowired
    private ServerRepositoryJPA serverRepository;

    public ServerHarvesterResponse execute(UserId userId, ServerId serverId, ServerHarvesterId serverHarvesterId) {
        Server server = serverRepository.ofId(serverId);
        if (!server.user().userId().equals(userId)) {
            throw new ServerNotFoundException();
        }
        return new ServerHarvesterResponse(server.harvester(serverHarvesterId));
    }
}
