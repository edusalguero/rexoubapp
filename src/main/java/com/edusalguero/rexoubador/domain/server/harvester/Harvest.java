package com.edusalguero.rexoubador.domain.server.harvester;


import com.edusalguero.rexoubador.application.datatransformer.DateConverter;

import java.util.Date;

public class Harvest {
    private Date date;
    private String data;

    public Harvest(Date date, String data) {
        this.date = date;
        this.data = data;
    }

    public String getDate() {
        return DateConverter.getFormattedDateOrEmptyString(date);
    }

    public String getData() {
        return data;
    }
}
