package com.edusalguero.rexoubapp.domain.model.monitor.harvester;

import com.edusalguero.rexoubapp.domain.shared.NotFoundException;

public class HarvesterNotFoundException extends NotFoundException {
    public HarvesterNotFoundException() {
        super("00003","Harvester not found!");
    }
}
