package com.edusalguero.rexoubador.domain.model.monitor.observer;


import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubador.domain.service.executor.command.CommandInterface;
import com.edusalguero.rexoubador.domain.service.executor.command.ServiceStatus;

public class ObserverCommandFactory {

    public CommandInterface make(ServerObserver serverObserver) {
        switch (serverObserver.observer().type()) {
            case SERVICE:
                return new ServiceStatus(serverObserver.serverObserverId(), serverObserver.observer().name());
            default:
                throw new InvalidObserverTypeException();
        }
    }
}
