package com.edusalguero.rexoubapp.application.server.observer;

import com.edusalguero.rexoubapp.application.datatransformer.DateConverter;
import com.edusalguero.rexoubapp.application.monitor.observer.ObserverResponse;
import com.edusalguero.rexoubapp.domain.model.server.observer.Observation;
import com.edusalguero.rexoubapp.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubapp.domain.shared.CheckStatus;

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
