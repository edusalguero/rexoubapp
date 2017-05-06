package com.edusalguero.rexoubador.domain.monitor.harvester;

import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "harvester")
@Inheritance
@DiscriminatorColumn(name = "type")
abstract public class Harvester {
    @EmbeddedId
    protected HarvesterId harvesterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "label")
    protected String label;

    @Column(name = "type", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    protected HarvesterType type;

    @Column(name = "notify_warning")
    protected Boolean notifyWarning;

    @Column(name = "notify_alert")
    protected Boolean notifyAlert;

    @Column(name = "warning_value")
    protected String warningValue;

    @Column(name = "alert_value")
    protected String alertValue;


    @Column(name = "entry_date", columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date entryDate;

    @Enumerated(EnumType.STRING)
    protected Status status;

    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    public Harvester(User user, HarvesterId harvesterId, String label, Boolean notifyWarning, Boolean notifyAlert, String warningValue, String alertValue, Status status) {
        this.user = user;
        this.harvesterId = harvesterId;
        this.label = label;
        this.notifyWarning = notifyWarning;
        this.notifyAlert = notifyAlert;
        this.warningValue = warningValue;
        this.alertValue = alertValue;
        this.entryDate = new Date();
        this.status = status;
    }

    protected Harvester()
    {
        // Needed by JPA
    }


    public Boolean notifyWarning() {
        return notifyWarning;
    }

    public void notifyWarning(Boolean notifyWarning) {
        this.notifyWarning = notifyWarning;
    }

    public Boolean notifyAlert() {
        return notifyAlert;
    }

    public void notifyAlert(Boolean notifyAlert) {
        this.notifyAlert = notifyAlert;
    }

    public String warningValue() {
        return warningValue;
    }

    public void warningValue(String warningValue) {
        this.warningValue = warningValue;
    }

    public String alertValue() {
        return alertValue;
    }

    public void alertValue(String alertValue) {
        this.alertValue = alertValue;
    }

    public User user() {
        return user;
    }


    public HarvesterId harvesterId()
    {
        return harvesterId;
    }
    public String id() {
        return harvesterId.toString();
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


    public HarvesterType type() {
        return type;
    }
}
