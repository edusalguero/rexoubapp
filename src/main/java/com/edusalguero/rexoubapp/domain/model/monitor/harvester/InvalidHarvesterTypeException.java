package com.edusalguero.rexoubapp.domain.model.monitor.harvester;

import com.edusalguero.rexoubapp.domain.shared.RexoubappException;

public class InvalidHarvesterTypeException extends RexoubappException {
    public InvalidHarvesterTypeException() {
        super("00004","Invalid harvester type!");
    }
}
