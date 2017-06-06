package com.edusalguero.rexoubapp.domain.model.server.harvester;


import com.edusalguero.rexoubapp.domain.model.monitor.MonitorDataInterface;
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
