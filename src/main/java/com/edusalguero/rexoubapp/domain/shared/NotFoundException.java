package com.edusalguero.rexoubapp.domain.shared;

public class NotFoundException extends RexoubappException {

    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
