package com.worldfirst.test.exceptions;

public class BadCurrencyException extends Exception {

    private String currencyCode;

    public BadCurrencyException(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
