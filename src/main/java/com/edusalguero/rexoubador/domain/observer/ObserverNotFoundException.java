package com.edusalguero.rexoubador.domain.observer;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class ObserverNotFoundException extends NotFoundException {
    public ObserverNotFoundException() {
        super("Observer not found!");
    }
}
