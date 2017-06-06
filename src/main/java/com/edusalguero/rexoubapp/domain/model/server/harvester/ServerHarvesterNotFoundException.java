package com.edusalguero.rexoubapp.domain.model.server.harvester;

import com.edusalguero.rexoubapp.domain.shared.NotFoundException;

public class ServerHarvesterNotFoundException extends NotFoundException {
    public ServerHarvesterNotFoundException() {
        super("00007","Server harvester not found!");
    }
}
