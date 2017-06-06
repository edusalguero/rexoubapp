package com.edusalguero.rexoubapp.domain.service.executor;

import com.edusalguero.rexoubapp.domain.shared.RexoubadorException;


public class ExecutionException extends RexoubadorException {
    public ExecutionException(String message) {
        super("00002",message);
    }
}
