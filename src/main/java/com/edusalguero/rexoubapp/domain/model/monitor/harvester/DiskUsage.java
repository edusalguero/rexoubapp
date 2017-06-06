package com.edusalguero.rexoubapp.domain.model.monitor.harvester;


import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.shared.Status;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DISK_USAGE")
public class DiskUsage extends Harvester {
    public DiskUsage(User user, HarvesterId harvesterId, String label, Boolean notifyWarning, Boolean notifyAlert, String warningValue, String alertValue, Status status) {
        super(user, harvesterId, label, notifyWarning, notifyAlert, warningValue, alertValue, status);
        type = HarvesterType.DISK_USAGE;
    }

    protected DiskUsage() {
        // Needed by JPA
    }
}
