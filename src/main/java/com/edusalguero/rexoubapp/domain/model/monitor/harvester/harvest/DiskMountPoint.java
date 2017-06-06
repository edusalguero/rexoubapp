package com.edusalguero.rexoubapp.domain.model.monitor.harvester.harvest;

import com.edusalguero.rexoubapp.domain.model.monitor.MonitorDataInterface;
import com.google.gson.Gson;

public class DiskMountPoint implements MonitorDataInterface {

    private int total;
    private int percentageOfUse;
    private int used;
    private int free;
    private String type;
    private String filesystem;
    private String mountedOn;

    public DiskMountPoint(int total, int used, int free, int percentageOfUse, String type, String filesystem, String mountedOn) {
        this.total = total;
        this.percentageOfUse = percentageOfUse;
        this.used = used;
        this.free = free;
        this.type = type;
        this.filesystem = filesystem;
        this.mountedOn = mountedOn;
    }

    public int getTotal() {
        return total;
    }

    public int getPercentageOfUse() {
        return percentageOfUse;
    }

    public int getUsed() {
        return used;
    }

    public int getFree() {
        return free;
    }

    public String getType() {
        return type;
    }

    public String getFilesystem() {
        return filesystem;
    }

    public String getMountedOn() {
        return mountedOn;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
