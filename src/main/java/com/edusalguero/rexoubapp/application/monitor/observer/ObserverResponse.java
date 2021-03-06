package com.edusalguero.rexoubapp.application.monitor.observer;


import com.edusalguero.rexoubapp.application.datatransformer.DateConverter;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.Observer;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverType;
import com.edusalguero.rexoubapp.domain.shared.Status;

import java.util.Date;

public class ObserverResponse {
    private String observerId;
    private String label;
    private ObserverType type;
    private String name;
    private Boolean notifyStatusChanges;
    private Boolean notifyInactivity;
    private Date entryDate;
    private Status status;

    public ObserverResponse(Observer observer) {
        this.observerId = observer.id();
        this.label = observer.label();
        this.type = observer.type();
        this.name = observer.name();
        this.notifyStatusChanges = observer.notifyStatusChanges();
        this.notifyInactivity = observer.notifyInactivity();
        this.entryDate = observer.entryDate();
        this.status = observer.status();
    }

    public String getObserverId() {
        return observerId;
    }

    public String getLabel() {
        return label;
    }

    public ObserverType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Boolean getNotifyStatusChanges() {
        return notifyStatusChanges;
    }

    public Boolean getNotifyInactivity() {
        return notifyInactivity;
    }

    public String getEntryDate() {
        return DateConverter.getFormattedDateOrEmptyString(entryDate);
    }

    public Status getStatus() {
        return status;
    }
}
