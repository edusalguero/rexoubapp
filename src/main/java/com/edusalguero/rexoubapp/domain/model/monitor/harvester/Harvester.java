package com.edusalguero.rexoubapp.domain.model.monitor.harvester;

import com.edusalguero.rexoubapp.domain.model.monitor.Monitor;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.shared.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "harvester")
@Inheritance
@DiscriminatorColumn(name = "type")
abstract public class Harvester extends Monitor{
    @EmbeddedId
    protected HarvesterId harvesterId;

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

    public Harvester(User user, HarvesterId harvesterId, String label, Boolean notifyWarning, Boolean notifyAlert, String warningValue, String alertValue, Status status) {
        this.user = user;
        this.harvesterId = harvesterId;
        setLabel(label);
        this.notifyWarning = notifyWarning;
        this.notifyAlert = notifyAlert;
        this.warningValue = warningValue;
        this.alertValue = alertValue;
        this.entryDate = new Date();
        this.status = status;
    }

    protected Harvester() {
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

    public HarvesterId harvesterId() {
        return harvesterId;
    }

    public String id() {
        return harvesterId.toString();
    }

    public HarvesterType type() {
        return type;
    }
}
