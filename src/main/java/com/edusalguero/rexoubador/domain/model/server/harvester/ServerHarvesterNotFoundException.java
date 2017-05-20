package com.edusalguero.rexoubador.domain.model.server.harvester;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class ServerHarvesterNotFoundException extends NotFoundException {
    public ServerHarvesterNotFoundException() {
        super("00007","Server harvester not found!");
    }
}
