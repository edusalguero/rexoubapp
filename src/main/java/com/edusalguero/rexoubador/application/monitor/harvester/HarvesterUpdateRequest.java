package com.edusalguero.rexoubador.application.monitor.harvester;

import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.user.UserId;

public class HarvesterUpdateRequest {
    private final HarvesterId harvesterId;
    private final UserId userId;
    private final String label;
    private final Boolean notifyWarning;
    private final Boolean notifyAlert;
    private final String warningValue;
    private final String alertValue;
    private final Status status;

    public HarvesterUpdateRequest(HarvesterId harvesterId, UserId userId, String label, Boolean notifyWarning, Boolean notifyAlert, String warningValue, String alertValue, Status status) {
        this.harvesterId = harvesterId;
        this.userId = userId;
        this.label = label;
        this.notifyWarning = notifyWarning;
        this.notifyAlert = notifyAlert;
        this.warningValue = warningValue;
        this.alertValue = alertValue;
        this.status = status;
    }

    public HarvesterId getHarvesterId() {
        return harvesterId;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getNotifyWarning() {
        return notifyWarning;
    }

    public Boolean getNotifyAlert() {
        return notifyAlert;
    }

    public String getWarningValue() {
        return warningValue;
    }

    public String getAlertValue() {
        return alertValue;
    }

    public Status getStatus() {
        return status;
    }


}
