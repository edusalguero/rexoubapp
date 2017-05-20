package com.edusalguero.rexoubador.domain.model.monitor.harvester;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class HarvesterNotFoundException extends NotFoundException {
    public HarvesterNotFoundException() {
        super("00003","Harvester not found!");
    }
}
