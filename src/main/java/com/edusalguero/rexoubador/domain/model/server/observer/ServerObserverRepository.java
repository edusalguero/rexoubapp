package com.edusalguero.rexoubador.domain.model.server.observer;

import com.edusalguero.rexoubador.domain.model.server.Server;

import java.util.Collection;


public interface ServerObserverRepository {

    ServerObserver ofId(ServerObserverId serverObserverId);

    Collection<ServerObserver> ofServer(Server server);

    ServerObserverId nextIdentity();

}
