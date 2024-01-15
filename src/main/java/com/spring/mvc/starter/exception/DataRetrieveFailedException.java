package com.spring.mvc.starter.exception;

import java.sql.SQLException;

public class DataRetrieveFailedException extends RuntimeException {

    private final String message;

    public DataRetrieveFailedException(String message, SQLException ex) {
        super(message, ex);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
