package com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest;

import com.edusalguero.rexoubador.domain.model.monitor.MonitorDataInterface;
import com.google.gson.Gson;

public class MemoryUsageHarvest implements MonitorDataInterface {
    private int total;
    private int used;
    private int free;

    public MemoryUsageHarvest(int total, int used, int free) {
        this.total = total;
        this.used = used;
        this.free = free;
    }

    public int getTotal() {
        return total;
    }

    public int getUsed() {
        return used;
    }

    public int getFree() {
        return free;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
