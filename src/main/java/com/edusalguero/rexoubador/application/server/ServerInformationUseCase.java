package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.ServerId;
import com.edusalguero.rexoubador.domain.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ServerRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerInformationUseCase {

    @Autowired
    private ServerRepositoryJPA serverRepository;

    public ServerResponse execute(ServerId serverId, UserId userId)
    {
        Server server = serverRepository.ofId(serverId);
        if(!server.user().id().equals(userId.getId()))
        {
            throw new ServerNotFoundException();
        }

        return new ServerResponse(server);
    }
}
