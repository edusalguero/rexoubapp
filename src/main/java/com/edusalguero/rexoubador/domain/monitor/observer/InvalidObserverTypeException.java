package com.edusalguero.rexoubador.domain.monitor.observer;

import com.edusalguero.rexoubador.domain.shared.RexoubadorException;

public class InvalidObserverTypeException extends RexoubadorException {
    public InvalidObserverTypeException() {
        super("Invalid observer type!");
    }
}
