package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.ServerNotFoundException;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ServerRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerUpdateUseCase {

    @Autowired
    private ServerRepositoryJPA serverRepository;

    public void execute(ServerUpdateRequest serverUpdateRequest) {
        Server server = serverRepository.ofId(serverUpdateRequest.getServerId());
        if (!server.user().id().equals(serverUpdateRequest.getUserId().getId())) {
            throw new ServerNotFoundException();
        }

        if (serverUpdateRequest.ipHasChanged()) {
            server.updateIp(serverUpdateRequest.getIp());
        }

        if (serverUpdateRequest.labelHasChanged()) {
            server.updateLabel(serverUpdateRequest.getLabel());
        }

        if (serverUpdateRequest.statusHasChanged()) {
            server.updateStatus(serverUpdateRequest.getStatus());
        }
        serverRepository.update(server);
    }
}
