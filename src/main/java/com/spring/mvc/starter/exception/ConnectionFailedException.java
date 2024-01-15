package com.spring.mvc.starter.exception;

import java.sql.SQLException;

public class ConnectionFailedException extends RuntimeException {

    private final String message;

    public ConnectionFailedException(String message, SQLException ex) {
        super(message, ex);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
