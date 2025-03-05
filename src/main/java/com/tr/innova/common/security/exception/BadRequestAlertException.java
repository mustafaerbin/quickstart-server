package com.tr.innova.common.security.exception;

public class BadRequestAlertException extends RuntimeException {

    private final String message;

    public BadRequestAlertException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
