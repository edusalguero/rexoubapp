package com.edusalguero.rexoubador.domain.service.executor;

import com.edusalguero.rexoubador.domain.shared.RexoubadorException;


public class ExecutionException extends RexoubadorException {
    public ExecutionException(String message) {
        super("00002",message);
    }
}
