package com.edusalguero.rexoubapp.domain.shared;


public class RexoubappException extends RuntimeException {
    private String code = "00000";
    public RexoubappException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
