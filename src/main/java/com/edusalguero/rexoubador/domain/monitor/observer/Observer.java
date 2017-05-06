package com.edusalguero.rexoubador.domain.monitor.observer;

import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.user.User;

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
    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    public Observer(User user, ObserverId observerId, String name, String label, Boolean notifyStatusChanges, Boolean notifyInactivity, Status status) {
        this.user = user;
        this.observerId = observerId;
        this.name = name;
        this.label = label;
        this.notifyStatusChanges = notifyStatusChanges;
        this.notifyInactivity = notifyInactivity;
        this.entryDate = new Date();
        this.status = status;
    }

    protected Observer()
    {
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


    public ObserverId observerId()
    {
        return observerId;
    }
    public String id() {
        return observerId.toString();
    }


    public void updateStatus(Status status) {
        this.status = status;
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
        this.label = label;
    }

    public String name() {
        return name;
    }

    public ObserverType type() {
        return type;
    }
}
