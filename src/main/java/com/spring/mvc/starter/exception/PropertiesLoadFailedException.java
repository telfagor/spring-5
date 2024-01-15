package com.spring.mvc.starter.exception;

import java.io.IOException;

public class PropertiesLoadFailedException extends RuntimeException {

    private final String message;

    public PropertiesLoadFailedException(String message, IOException ex) {
        super(message, ex);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
