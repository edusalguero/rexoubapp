package com.edusalguero.rexoubapp.application.server.observer;

import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterNotFoundException;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.Observer;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverId;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverRepository;
import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubapp.domain.model.server.ServerRepository;
import com.edusalguero.rexoubapp.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubapp.domain.model.server.observer.ServerObserverRepository;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerObserverCreateUseCase {

    private final ServerRepository serverRepository;

    private final ObserverRepository observerRepository;

    private final ServerObserverRepository serverObserverRepository;

    @Autowired
    public ServerObserverCreateUseCase(ServerRepository serverRepository, ObserverRepository observerRepository, ServerObserverRepository serverObserverRepository) {
        this.serverRepository = serverRepository;
        this.observerRepository = observerRepository;
        this.serverObserverRepository = serverObserverRepository;
    }

    public ServerObserverId execute(UserId userId, ServerId serverId, ObserverId observerId) {
        Server server = serverRepository.ofId(serverId);
        if (!server.user().userId().equals(userId)) {
            throw new ServerNotFoundException();
        }

        Observer observer = observerRepository.ofId(observerId);
        if (!observer.user().userId().equals(userId)) {
            throw new HarvesterNotFoundException();
        }

        ServerObserverId serverObserverId = serverObserverRepository.nextIdentity();
        server.addObserver(serverObserverId, observer);
        serverRepository.update(server);
        return serverObserverId;
    }
}
