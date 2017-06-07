package com.edusalguero.rexoubapp.domain.model.monitor.observer;

import com.edusalguero.rexoubapp.domain.shared.RexoubappException;

public class InvalidObserverTypeException extends RexoubappException {
    public InvalidObserverTypeException() {
        super("00005","Invalid observer type!");
    }
}
