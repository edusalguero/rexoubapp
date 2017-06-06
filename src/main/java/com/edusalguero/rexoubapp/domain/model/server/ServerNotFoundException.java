package com.edusalguero.rexoubapp.domain.model.server;

import com.edusalguero.rexoubapp.domain.shared.NotFoundException;

public class ServerNotFoundException extends NotFoundException {
    public ServerNotFoundException() {
        super("00008","Server not found!");
    }
}
