package com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest;


import com.edusalguero.rexoubador.domain.model.monitor.MonitorDataInterface;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.InvalidHarvesterTypeException;
import com.google.gson.Gson;

public class HarvestFactory {
    public MonitorDataInterface make(Harvester harvester, String harvestData) {
        Gson gson = new Gson();
        switch (harvester.type()) {
            case LOAD:
                return gson.fromJson(harvestData, LoadHarvest.class);
            case DISK_USAGE:
                DiskUsageHarvest diskUsageHarvest = new DiskUsageHarvest();
                DiskMountPoint[] diskMountPoints = gson.fromJson(harvestData, DiskMountPoint[].class);
                diskUsageHarvest.setMountPoints(diskMountPoints);
                return diskUsageHarvest;
            case MEMORY_USAGE:
                return gson.fromJson(harvestData, MemoryUsageHarvest.class);
            default:
                throw new InvalidHarvesterTypeException();
        }
    }
}
