package com.edusalguero.rexoubador.application.monitor.observer;

import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.monitor.observer.ObserverId;
import com.edusalguero.rexoubador.domain.user.UserId;

public class ObserverUpdateRequest {
    private final ObserverId observerId;
    private final UserId userId;
    private final String label;
    private final Boolean notifyStatusChanges;
    private final Boolean notifyInactivity;

    public ObserverUpdateRequest(ObserverId observerId, UserId userId, String label, Boolean notifyStatusChanges, Boolean notifyInactivity, Status status) {
        this.observerId = observerId;
        this.userId = userId;
        this.label = label;
        this.notifyStatusChanges = notifyStatusChanges;
        this.notifyInactivity = notifyInactivity;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getNotifyStatusChanges() {
        return notifyStatusChanges;
    }

    public Boolean getNotifyInactivity() {
        return notifyInactivity;
    }

    public ObserverId getObserverId() {
        return observerId;
    }

    public UserId getUserId() {
        return userId;
    }
}
