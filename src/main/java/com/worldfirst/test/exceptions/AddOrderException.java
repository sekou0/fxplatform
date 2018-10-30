package com.worldfirst.test.exceptions;

public class AddOrderException extends Exception {

    private String httpCode;
    private String message;

    public AddOrderException(String code, String message) {

        this.httpCode = code;
        this.message = message;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
