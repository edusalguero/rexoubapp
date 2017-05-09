package com.edusalguero.rexoubador.domain.model.monitor.harvester;

import com.edusalguero.rexoubador.domain.shared.RexoubadorException;

public class InvalidHarvesterTypeException extends RexoubadorException {
    public InvalidHarvesterTypeException() {
        super("Invalid harvester type!");
    }
}