package com.edusalguero.rexoubador.application.server.harvester;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.ServerId;
import com.edusalguero.rexoubador.domain.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.server.harvester.ServerHarvester;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ServerRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ServerHarvestersUseCase {

    @Autowired
    private ServerRepositoryJPA serverRepository;

    public ArrayList<ServerHarvesterResponse> execute(UserId userId, ServerId serverId) {
        Server server = serverRepository.ofId(serverId);
        ArrayList<ServerHarvesterResponse> serverHarvesters = new ArrayList<>();
        if (!server.user().userId().equals(userId)) {
            throw new ServerNotFoundException();
        }
        for (ServerHarvester harvester : server.harvesters()) {
            serverHarvesters.add(new ServerHarvesterResponse(harvester));
        }
        return serverHarvesters;
    }
}
