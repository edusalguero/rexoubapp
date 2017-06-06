package com.edusalguero.rexoubapp.domain.model.monitor.harvester;

import com.edusalguero.rexoubapp.domain.shared.RexoubadorException;

public class InvalidHarvesterTypeException extends RexoubadorException {
    public InvalidHarvesterTypeException() {
        super("00004","Invalid harvester type!");
    }
}
