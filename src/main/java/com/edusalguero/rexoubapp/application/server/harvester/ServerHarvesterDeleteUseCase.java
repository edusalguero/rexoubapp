package com.edusalguero.rexoubapp.application.server.harvester;

import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubapp.domain.model.server.ServerRepository;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerHarvesterDeleteUseCase {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerHarvesterDeleteUseCase(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public void execute(UserId userId, ServerId serverId, ServerHarvesterId serverHarvesterId) {
        Server server = serverRepository.ofId(serverId);
        if (!server.user().userId().equals(userId)) {
            throw new ServerNotFoundException();
        }
        server.deleteHarvester(serverHarvesterId);
        serverRepository.update(server);
    }
}
