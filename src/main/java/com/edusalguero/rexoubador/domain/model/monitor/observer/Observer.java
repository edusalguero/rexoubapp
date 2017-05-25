package com.edusalguero.rexoubador.domain.model.monitor.observer;

import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.shared.Status;
import com.edusalguero.rexoubador.domain.shared.ValidationException;
import com.edusalguero.rexoubador.domain.shared.validator.LabelValidator;
import com.edusalguero.rexoubador.domain.shared.validator.ObserverNameValidator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "observer")
@Inheritance
@DiscriminatorColumn(name = "type")
abstract public class Observer {
    @EmbeddedId
    protected ObserverId observerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "label")
    protected String label;

    @Column(name = "type", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    protected ObserverType type;

    @Column(name = "name")
    protected String name;

    @Column(name = "notify_status_changes")
    protected Boolean notifyStatusChanges;

    @Column(name = "notify_inactivity")
    protected Boolean notifyInactivity;

    @Column(name = "entry_date", columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date entryDate;

    @Enumerated(EnumType.STRING)
    protected Status status;

    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

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

    private void setLabel(String label) {
        LabelValidator labelValidator = new LabelValidator();
        if(!labelValidator.validate(label))
        {
            throw new ValidationException("Invalid label.");
        }
        this.label = label;
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

    public User user() {
        return user;
    }


    public ObserverId observerId() {
        return observerId;
    }

    public void disable() {
        status = Status.DISABLED;
    }

    public void enable() {
        status = Status.ENABLED;
    }

    public String id() {
        return observerId.toString();
    }

    public Date entryDate() {
        return entryDate;
    }

    public Status status() {
        return status;
    }

    public Boolean isSoftDeleted() {
        return status == Status.DELETED;
    }

    public void delete() {
        status = Status.DELETED;
    }

    public String label() {
        return label;
    }

    public void label(String label) {
        setLabel(label);
    }

    public String name() {
        return name;
    }

    public ObserverType type() {
        return type;
    }
}
