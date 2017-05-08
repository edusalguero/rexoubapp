package com.edusalguero.rexoubador.domain.model.server;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class ServerNotFoundException extends NotFoundException {
    public ServerNotFoundException() {
        super("Server not found!");
    }
}
