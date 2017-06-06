package com.edusalguero.rexoubapp.domain.model.monitor.harvester.harvest;

import com.edusalguero.rexoubapp.domain.model.monitor.MonitorDataInterface;
import com.google.gson.Gson;

public class LoadHarvest implements MonitorDataInterface {
    private final float last;
    private final float last5mins;
    private final float last15mins;

    public LoadHarvest(float last, float last5mins, float last15mins) {
        this.last = last;
        this.last5mins = last5mins;
        this.last15mins = last15mins;
    }

    public float getLast() {
        return last;
    }

    public float getLast5mins() {
        return last5mins;
    }

    public float getLast15mins() {
        return last15mins;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
