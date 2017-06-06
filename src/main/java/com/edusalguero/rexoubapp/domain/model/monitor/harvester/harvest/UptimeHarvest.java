package com.edusalguero.rexoubapp.domain.model.monitor.harvester.harvest;

import com.edusalguero.rexoubapp.domain.model.monitor.MonitorDataInterface;

public class UptimeHarvest implements MonitorDataInterface {

    private int uptime;

    public UptimeHarvest(int uptime) {
        this.uptime = uptime;
    }

    public int getUptime() {
        return uptime;
    }

    @Override
    public String toJson() {
        return String.valueOf(uptime);
    }
}
