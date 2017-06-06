package com.edusalguero.rexoubapp.domain.model.server.harvester;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;

public class NullHarvest extends Harvest {
    NullHarvest() {
        super(null, null);
    }

    @Override
    public Date getDate() {
        return null;
    }

    @JsonValue
    @JsonRawValue
    @Override
    public String getData() {
        return "{}";
    }
}
