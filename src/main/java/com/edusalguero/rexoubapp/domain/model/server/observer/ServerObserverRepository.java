package com.edusalguero.rexoubapp.domain.model.server.observer;

import com.edusalguero.rexoubapp.domain.model.server.Server;

import java.util.Collection;


public interface ServerObserverRepository {

    ServerObserver ofId(ServerObserverId serverObserverId);

    Collection<ServerObserver> ofServer(Server server);

    ServerObserverId nextIdentity();

}
