package com.edusalguero.rexoubapp.application.monitor.harvester;


import com.edusalguero.rexoubapp.application.datatransformer.DateConverter;
import com.edusalguero.rexoubapp.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubapp.domain.shared.Status;

import java.util.Date;

public class HarvesterResponse {
    private final Boolean notifyWarning;
    private final Boolean notifyAlert;
    private final String warningValue;
    private final String alertValue;
    private final String harvesterId;
    private String label;
    private HarvesterType type;
    private Date entryDate;
    private Status status;

    public HarvesterResponse(Harvester harvester) {
        this.harvesterId = harvester.id();
        this.label = harvester.label();
        this.type = harvester.type();
        this.notifyWarning = harvester.notifyWarning();
        this.notifyAlert = harvester.notifyAlert();
        this.warningValue = harvester.warningValue();
        this.alertValue = harvester.alertValue();
        this.entryDate = harvester.entryDate();
        this.status = harvester.status();
    }

    public String getHarvesterId() {
        return harvesterId;
    }

    public String getLabel() {
        return label;
    }

    public HarvesterType getType() {
        return type;
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


    public String getEntryDate() {
        return DateConverter.getFormattedDateOrEmptyString(entryDate);
    }

    public Status getStatus() {
        return status;
    }
}
