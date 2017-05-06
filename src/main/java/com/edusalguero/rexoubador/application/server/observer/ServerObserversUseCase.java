package com.edusalguero.rexoubador.application.server.observer;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.ServerId;
import com.edusalguero.rexoubador.domain.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.server.observer.ServerObserver;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ServerRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServerObserversUseCase {

    @Autowired
    private ServerRepositoryJPA serverRepository;

    public ArrayList<ServerObserverResponse> execute(UserId userId, ServerId serverId) {
        Server server = serverRepository.ofId(serverId);
        ArrayList<ServerObserverResponse> serverObservers = new ArrayList<>();
        if (!server.user().userId().equals(userId)) {
            throw new ServerNotFoundException();
        }
        for (ServerObserver observer : server.observers()) {
            serverObservers.add(new ServerObserverResponse(observer));
        }
        return serverObservers;
    }
}
