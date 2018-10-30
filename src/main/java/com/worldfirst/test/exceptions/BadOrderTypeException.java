package com.worldfirst.test.exceptions;

public class BadOrderTypeException extends Exception {

    private String orderType;

    public BadOrderTypeException(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
