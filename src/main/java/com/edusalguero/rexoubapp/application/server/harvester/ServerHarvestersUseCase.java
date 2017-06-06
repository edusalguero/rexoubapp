package com.edusalguero.rexoubapp.application.server.harvester;

import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubapp.domain.model.server.ServerRepository;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvester;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServerHarvestersUseCase {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerHarvestersUseCase(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

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
