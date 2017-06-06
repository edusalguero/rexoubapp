package com.edusalguero.rexoubapp.domain.model.server.observer;

import com.edusalguero.rexoubapp.domain.shared.NotFoundException;

public class ServerObserverNotFoundException extends NotFoundException {
    public ServerObserverNotFoundException() {
        super("00009","Server harvester not found!");
    }
}
