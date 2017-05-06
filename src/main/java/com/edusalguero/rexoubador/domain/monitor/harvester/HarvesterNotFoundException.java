package com.edusalguero.rexoubador.domain.monitor.harvester;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class HarvesterNotFoundException extends NotFoundException {
    public HarvesterNotFoundException() {
        super("Harvester not found!");
    }
}
