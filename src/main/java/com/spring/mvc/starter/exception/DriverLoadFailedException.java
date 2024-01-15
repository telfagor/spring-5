package com.spring.mvc.starter.exception;

public class DriverLoadFailedException extends RuntimeException {

    private final String message;

    public DriverLoadFailedException(String message, ClassNotFoundException ex) {
        super(message, ex);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
