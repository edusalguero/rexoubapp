package com.edusalguero.rexoubapp.application.monitor.observer;

import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverId;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.shared.Status;

public class ObserverUpdateRequest {
    private final ObserverId observerId;
    private final UserId userId;
    private final String label;
    private final Boolean notifyStatusChanges;
    private final Boolean notifyInactivity;
    private final Status status;

    public ObserverUpdateRequest(ObserverId observerId, UserId userId, String label, Boolean notifyStatusChanges, Boolean notifyInactivity, Status status) {
        this.observerId = observerId;
        this.userId = userId;
        this.label = label;
        this.notifyStatusChanges = notifyStatusChanges;
        this.notifyInactivity = notifyInactivity;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }
}
