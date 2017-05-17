package com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest;

import com.edusalguero.rexoubador.domain.model.monitor.MonitorDataInterface;
import com.google.gson.Gson;

public class UptimeHarvest implements MonitorDataInterface{

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
