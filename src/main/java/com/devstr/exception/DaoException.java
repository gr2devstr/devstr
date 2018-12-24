package com.devstr.exception;

public class DaoException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Server-side issues.";

    public DaoException() {
        super(DEFAULT_MESSAGE);
    }

    public DaoException(String message) {
        super(DEFAULT_MESSAGE + " Message: " + message);
    }

}
