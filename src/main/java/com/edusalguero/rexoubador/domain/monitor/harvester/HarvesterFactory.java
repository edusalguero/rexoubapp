package com.edusalguero.rexoubador.domain.monitor.harvester;

import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.monitor.observer.InvalidObserverTypeException;
import com.edusalguero.rexoubador.domain.user.User;

public class HarvesterFactory {

    public Harvester make(User user, HarvesterId harvesterId, HarvesterType type, String label, Boolean notifyWarning, Boolean notifyAlert, String warningValue, String alertValue, Status status) {
        if (type == HarvesterType.DISK_USAGE) {
            return new DiskUsage(user, harvesterId, label, notifyWarning, notifyAlert, warningValue, alertValue, status);
        } else if (type == HarvesterType.LOAD) {
            return new Load(user, harvesterId, label, notifyWarning, notifyAlert, warningValue, alertValue, status);
        } else if (type == HarvesterType.MEMORY_USAGE) {
            return new MemoryUsage(user, harvesterId, label, notifyWarning, notifyAlert, warningValue, alertValue, status);
        }
        throw new InvalidObserverTypeException();
    }
}
