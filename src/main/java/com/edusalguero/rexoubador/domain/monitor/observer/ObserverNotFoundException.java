package com.edusalguero.rexoubador.domain.monitor.observer;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class ObserverNotFoundException extends NotFoundException {
    public ObserverNotFoundException() {
        super("Observer not found!");
    }
}
