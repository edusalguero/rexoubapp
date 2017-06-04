package com.edusalguero.rexoubador.infrastructure.spring.error;

class ApiErrorResponse {
    private String code;
    private String message;

    ApiErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String toJson() {
        return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
    }
}
