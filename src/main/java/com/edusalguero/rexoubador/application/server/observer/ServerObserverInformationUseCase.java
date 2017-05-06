package com.edusalguero.rexoubador.application.server.observer;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.ServerId;
import com.edusalguero.rexoubador.domain.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ServerRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerObserverInformationUseCase {
    @Autowired
    private ServerRepositoryJPA serverRepository;

    public ServerObserverResponse execute(UserId userId, ServerId serverId, ServerObserverId serverObserverId) {
        Server server = serverRepository.ofId(serverId);
        if (!server.user().userId().equals(userId)) {
            throw new ServerNotFoundException();
        }
        return new ServerObserverResponse(server.observer(serverObserverId));
    }
}
