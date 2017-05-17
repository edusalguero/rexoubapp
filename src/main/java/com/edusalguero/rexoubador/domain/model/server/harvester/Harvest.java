package com.edusalguero.rexoubador.domain.model.server.harvester;


import com.edusalguero.rexoubador.domain.model.monitor.MonitorDataInterface;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;

public class Harvest {
    private Date date;
    private MonitorDataInterface data;

    Harvest(Date date, MonitorDataInterface data) {
        this.date = date;
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    @JsonValue
    @JsonRawValue
    public String getData() {
        return data.toJson();
    }
}
