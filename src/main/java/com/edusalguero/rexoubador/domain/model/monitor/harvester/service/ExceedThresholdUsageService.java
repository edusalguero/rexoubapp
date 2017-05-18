package com.edusalguero.rexoubador.domain.model.monitor.harvester.service;

import com.edusalguero.rexoubador.domain.model.monitor.MonitorDataInterface;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.InvalidHarvesterTypeException;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest.DiskMountPoint;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest.DiskUsageHarvest;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest.LoadHarvest;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest.MemoryUsageHarvest;

import java.util.ArrayList;

public class ExceedThresholdUsageService {

    private MonitorDataInterface monitorData;
    private MonitorDataInterface previousData;

    public ExceedThresholdUsageService(MonitorDataInterface monitorData, MonitorDataInterface previousData) {
        this.monitorData = monitorData;
        this.previousData = previousData;
    }

    public Boolean exceeded(Object threshold) {
        if (monitorData instanceof LoadHarvest) {
            Float currentUsage = ((LoadHarvest) monitorData).getLast() * 100;
            Float previousUsage = ((LoadHarvest) previousData).getLast() * 100;
            Float referenceValue = Float.parseFloat((String) threshold);
            if (currentUsage > previousUsage && currentUsage > referenceValue) {
                return true;
            }
        } else if (monitorData instanceof MemoryUsageHarvest) {
            Integer total = ((MemoryUsageHarvest) monitorData).getTotal();
            Integer free = ((MemoryUsageHarvest) monitorData).getFree();
            Integer currentUsage = 100 - ((100 * free) / total);

            Integer totalPrevious = ((MemoryUsageHarvest) previousData).getTotal();
            Integer freePrevious = ((MemoryUsageHarvest) previousData).getFree();
            Integer previousUsage = 100 - ((100 * freePrevious) / totalPrevious);

            Integer referenceValue = Integer.parseInt((String) threshold);
            if (currentUsage > previousUsage && currentUsage > referenceValue) {
                return true;
            }
        } else if (monitorData instanceof DiskUsageHarvest) {
            ArrayList<DiskMountPoint> diskMountPoints = ((DiskUsageHarvest) monitorData).getMountPoints();
            ArrayList<DiskMountPoint> previousMountPoints = ((DiskUsageHarvest) previousData).getMountPoints();
            Integer i = 0;
            Integer referenceValue = Integer.parseInt((String) threshold);
            for (DiskMountPoint point : diskMountPoints) {
                if (point.getPercentageOfUse() > previousMountPoints.get(i).getPercentageOfUse()
                        && point.getPercentageOfUse() > referenceValue) {
                    return true;
                }
            }
        } else {
            throw new InvalidHarvesterTypeException();
        }
        return false;
    }
}
