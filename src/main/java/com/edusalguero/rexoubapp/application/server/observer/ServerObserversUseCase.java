package com.edusalguero.rexoubapp.application.server.observer;

import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubapp.domain.model.server.ServerRepository;
import com.edusalguero.rexoubapp.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServerObserversUseCase {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerObserversUseCase(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

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
