package com.edusalguero.rexoubador.domain.model.server.harvester;


import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;

public class Harvest {
    private Date date;
    private String data;

    public Harvest(Date date, String data) {
        this.date = date;
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    @JsonValue
    @JsonRawValue
    public String getData() {
        return data;
    }
}
