package com.edusalguero.rexoubador.domain.server.observer;

import com.edusalguero.rexoubador.domain.server.Server;

import java.util.Collection;


public interface ServerObserverRepository {

    ServerObserver ofId(ServerObserverId serverObserverId);

    Collection<ServerObserver> ofServer(Server server);

    ServerObserverId nextIdentity();

}
