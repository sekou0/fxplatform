package com.worldfirst.test.domain;

/**
 * Created by kamau on 24/10/2018.
 */
public class OrderRequest {

    private Long userId;
    private String orderType;
    private String currency;
    private Float price;
    private Integer amount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Order toOrder() {

        Order order = new Order();

        order.setOrderType(OrderType.valueOf(this.orderType));
        order.setUserId(this.userId);
        order.setPrice(this.price);
        order.setCurrency(this.currency);
        order.setAmount(this.amount);

        return order;
    }
}
