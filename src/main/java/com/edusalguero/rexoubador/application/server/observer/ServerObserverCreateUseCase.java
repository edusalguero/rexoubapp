package com.edusalguero.rexoubador.application.server.observer;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterNotFoundException;
import com.edusalguero.rexoubador.domain.model.monitor.observer.Observer;
import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverId;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ObserverRepositoryJPA;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ServerObserverRepositoryJPA;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ServerRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerObserverCreateUseCase {

    @Autowired
    private ServerRepositoryJPA serverRepository;

    @Autowired
    private ObserverRepositoryJPA observerRepository;
    
    @Autowired
    private ServerObserverRepositoryJPA serverObserverRepository;

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
