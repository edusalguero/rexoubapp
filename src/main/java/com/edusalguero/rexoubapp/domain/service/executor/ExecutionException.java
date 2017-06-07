package com.edusalguero.rexoubapp.domain.service.executor;

import com.edusalguero.rexoubapp.domain.shared.RexoubappException;


public class ExecutionException extends RexoubappException {
    public ExecutionException(String message) {
        super("00002",message);
    }
}
