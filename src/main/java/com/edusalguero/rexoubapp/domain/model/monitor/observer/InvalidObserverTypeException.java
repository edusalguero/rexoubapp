package com.edusalguero.rexoubapp.domain.model.monitor.observer;

import com.edusalguero.rexoubapp.domain.shared.RexoubadorException;

public class InvalidObserverTypeException extends RexoubadorException {
    public InvalidObserverTypeException() {
        super("00005","Invalid observer type!");
    }
}
