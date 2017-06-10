package com.edusalguero.rexoubapp.domain.model.monitor.observer;

import com.edusalguero.rexoubapp.domain.model.monitor.Monitor;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.shared.Status;
import com.edusalguero.rexoubapp.domain.shared.ValidationException;
import com.edusalguero.rexoubapp.domain.shared.validator.ObserverNameValidator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "observer")
@Inheritance
@DiscriminatorColumn(name = "type")
abstract public class Observer extends Monitor {
    @EmbeddedId
    protected ObserverId observerId;

    @Column(name = "type", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    protected ObserverType type;

    @Column(name = "name")
    protected String name;

    @Column(name = "notify_status_changes")
    protected Boolean notifyStatusChanges;

    @Column(name = "notify_inactivity")
    protected Boolean notifyInactivity;

    public Observer(User user, ObserverId observerId, String name, String label, Boolean notifyStatusChanges, Boolean notifyInactivity, Status status) {
        this.user = user;
        this.observerId = observerId;
        setName(name);
        setLabel(label);
        this.notifyStatusChanges = notifyStatusChanges;
        this.notifyInactivity = notifyInactivity;
        this.entryDate = new Date();
        this.status = status;
    }

    private void setName(String name) {
        ObserverNameValidator observerNameValidator = new ObserverNameValidator();
        if(!observerNameValidator.validate(name))
        {
            throw new ValidationException("Invalid observer name.");
        }
        this.name = name;
    }

    protected Observer() {
        // Needed by JPA
    }

    public Boolean notifyStatusChanges() {
        return notifyStatusChanges;
    }

    public void notifyStatusChanges(Boolean notifyStatusChanges) {
        this.notifyStatusChanges = notifyStatusChanges;
    }

    public Boolean notifyInactivity() {
        return notifyInactivity;
    }

    public void notifyInactivity(Boolean notifyInactivity) {
        this.notifyInactivity = notifyInactivity;
    }

    public ObserverId observerId() {
        return observerId;
    }

    public String id() {
        return observerId.toString();
    }

    public String name() {
        return name;
    }

    public ObserverType type() {
        return type;
    }
}
