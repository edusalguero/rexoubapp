package com.edusalguero.rexoubador.domain.server.harvester;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class ServerHarvesterNotFoundException extends NotFoundException {
    public ServerHarvesterNotFoundException() {
        super("Server harvester not found!");
    }
}
