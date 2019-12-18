package com.j7arsen.data.exception;

public class BaseAppException extends Exception {

    private String message;

    public BaseAppException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
