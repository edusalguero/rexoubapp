package com.edusalguero.rexoubador.application.server.observer;

import com.edusalguero.rexoubador.application.datatransformer.DateConverter;
import com.edusalguero.rexoubador.application.monitor.observer.ObserverResponse;
import com.edusalguero.rexoubador.domain.model.server.observer.Observation;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubador.domain.shared.CheckStatus;

public class ServerObserverResponse {
    private ObserverResponse observerResponse;
    private Observation observation;
    private String serverObserverId;

    public ServerObserverResponse(ServerObserver serverObserver) {
        this.observerResponse = new ObserverResponse(serverObserver.observer());
        this.observation = serverObserver.getLastObservation();
        this.serverObserverId = serverObserver.id();
    }

    public String getObservationDate() {
        return DateConverter.getFormattedDateOrEmptyString(observation.getDate());
    }

    public CheckStatus getStatus() {
        return observation.getCheckStatus();
    }

    public String getServerObserverId() {
        return serverObserverId;
    }

    public ObserverResponse getObserver() {
        return observerResponse;
    }

}
