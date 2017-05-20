package com.edusalguero.rexoubador.domain.model.server.observer;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class ServerObserverNotFoundException extends NotFoundException {
    public ServerObserverNotFoundException() {
        super("00009","Server harvester not found!");
    }
}
