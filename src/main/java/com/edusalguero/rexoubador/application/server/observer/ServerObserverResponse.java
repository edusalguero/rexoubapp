package com.edusalguero.rexoubador.application.server.observer;

import com.edusalguero.rexoubador.application.monitor.observer.ObserverResponse;
import com.edusalguero.rexoubador.domain.server.observer.Observation;
import com.edusalguero.rexoubador.domain.server.observer.ServerObserver;

public class ServerObserverResponse {
    private ObserverResponse observerResponse;
    private Observation observation;
    private String serverObserverId;

    public ServerObserverResponse(ServerObserver serverObserver) {
        this.observerResponse = new ObserverResponse(serverObserver.observer());
        this.observation = serverObserver.getLastObservation();
        this.serverObserverId = serverObserver.id();
    }

    public String getServerObserverId() {
        return serverObserverId;
    }

    public Observation getObservation() {
        return observation;
    }

    public ObserverResponse getObserverResponse() {
        return observerResponse;
    }

}
