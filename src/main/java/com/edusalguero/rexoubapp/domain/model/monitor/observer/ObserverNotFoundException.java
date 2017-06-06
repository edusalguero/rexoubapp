package com.edusalguero.rexoubapp.domain.model.monitor.observer;

import com.edusalguero.rexoubapp.domain.shared.NotFoundException;

public class ObserverNotFoundException extends NotFoundException {
    public ObserverNotFoundException() {
        super("00006","Observer not found!");
    }
}
