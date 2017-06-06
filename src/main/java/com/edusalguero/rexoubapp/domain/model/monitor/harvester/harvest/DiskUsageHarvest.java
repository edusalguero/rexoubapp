package com.edusalguero.rexoubapp.domain.model.monitor.harvester.harvest;

import com.edusalguero.rexoubapp.domain.model.monitor.MonitorDataInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

//

public class DiskUsageHarvest implements MonitorDataInterface {
    private ArrayList<DiskMountPoint> mountPoints = new ArrayList<>();

    public void addMountPoint(DiskMountPoint mountPoint) {
        mountPoints.add(mountPoint);
    }

    void setMountPoints(DiskMountPoint[] mountPoints) {
        this.mountPoints = new ArrayList<>(Arrays.asList(mountPoints));
    }

    public ArrayList<DiskMountPoint> getMountPoints() {
        return mountPoints;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this.mountPoints);
    }
}
