package com.edusalguero.rexoubapp.domain.shared;

public class NotFoundException extends RexoubadorException {

    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
