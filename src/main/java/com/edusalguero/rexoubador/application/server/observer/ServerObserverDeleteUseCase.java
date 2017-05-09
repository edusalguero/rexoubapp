package com.edusalguero.rexoubador.application.server.observer;

import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.model.server.ServerRepository;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerObserverDeleteUseCase {

    @Autowired
    private ServerRepository serverRepository;

    public void execute(UserId userId, ServerId serverId, ServerObserverId serverObserverId) {
        Server server = serverRepository.ofId(serverId);
        if (!server.user().userId().equals(userId)) {
            throw new ServerNotFoundException();
        }
        server.deleteObserver(serverObserverId);
        serverRepository.update(server);
    }
}
