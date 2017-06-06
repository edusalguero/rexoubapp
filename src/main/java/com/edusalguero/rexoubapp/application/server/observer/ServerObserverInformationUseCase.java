package com.edusalguero.rexoubapp.application.server.observer;

import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubapp.domain.model.server.ServerRepository;
import com.edusalguero.rexoubapp.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerObserverInformationUseCase {
    private final ServerRepository serverRepository;

    @Autowired
    public ServerObserverInformationUseCase(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public ServerObserverResponse execute(UserId userId, ServerId serverId, ServerObserverId serverObserverId) {
        Server server = serverRepository.ofId(serverId);
        if (!server.user().userId().equals(userId)) {
            throw new ServerNotFoundException();
        }
        return new ServerObserverResponse(server.observer(serverObserverId));
    }
}
