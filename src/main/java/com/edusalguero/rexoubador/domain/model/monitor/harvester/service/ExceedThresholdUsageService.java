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

    public Boolean exceeded(Object referenceValue) {
        if (monitorData instanceof LoadHarvest) {
            Float currentUsage = ((LoadHarvest) monitorData).getLast() * 100;
            Float previousUsage = ((LoadHarvest) previousData).getLast() * 100;
            if (currentUsage > previousUsage && currentUsage > Float.parseFloat((String) referenceValue)) {
                return true;
            }
        } else if (monitorData instanceof MemoryUsageHarvest) {
            Integer total = ((MemoryUsageHarvest) monitorData).getTotal();
            Integer free = ((MemoryUsageHarvest) monitorData).getFree();
            Integer currentUsage = 100 - ((100 * free) / total);

            Integer totalPrevious = ((MemoryUsageHarvest) previousData).getTotal();
            Integer freePrevious = ((MemoryUsageHarvest) previousData).getFree();
            Integer previousUsage = 100 - ((100 * freePrevious) / totalPrevious);

            if (currentUsage > previousUsage && currentUsage > Integer.parseInt((String) referenceValue)) {
                return true;
            }
        } else if (monitorData instanceof DiskUsageHarvest) {
            ArrayList<DiskMountPoint> diskMountPoints = ((DiskUsageHarvest) monitorData).getMountPoints();
            ArrayList<DiskMountPoint> previousMountPoints = ((DiskUsageHarvest) previousData).getMountPoints();
            Integer i = 0;
            for (DiskMountPoint point : diskMountPoints) {
                if (point.getPercentageOfUse() > previousMountPoints.get(i).getPercentageOfUse()
                        && point.getPercentageOfUse() > Integer.parseInt((String) referenceValue)) {
                    return true;
                }
            }
        } else {
            throw new InvalidHarvesterTypeException();
        }
        return false;
    }
}
