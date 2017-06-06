package com.edusalguero.rexoubapp.domain.model.monitor.observer;


import com.edusalguero.rexoubapp.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubapp.domain.service.executor.command.CommandInterface;
import com.edusalguero.rexoubapp.domain.service.executor.command.ServiceStatusCommand;

public class ObserverCommandFactory {

    public CommandInterface make(ServerObserver serverObserver) {
        switch (serverObserver.observer().type()) {
            case SERVICE:
                return new ServiceStatusCommand(serverObserver.serverObserverId(), serverObserver.observer().name());
            default:
                throw new InvalidObserverTypeException();
        }
    }
}
