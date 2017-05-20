package com.edusalguero.rexoubador.domain.shared;


public class RexoubadorException extends RuntimeException {
    private String code = "00000";
    public RexoubadorException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
