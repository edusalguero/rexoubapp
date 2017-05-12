package com.edusalguero.rexoubador.domain.model.monitor.harvester;


import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.shared.Status;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MEMORY_USAGE")
public class MemoryUsage extends Harvester {
    public MemoryUsage(User user, HarvesterId harvesterId, String label, Boolean notifyWarning, Boolean notifyAlert, String warningValue, String alertValue, Status status) {
        super(user, harvesterId, label, notifyWarning, notifyAlert, warningValue, alertValue, status);
        type = HarvesterType.MEMORY_USAGE;
    }

    protected MemoryUsage() {
        // Needed by JPA
    }
}
