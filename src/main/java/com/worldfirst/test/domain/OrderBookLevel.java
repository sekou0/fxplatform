package com.worldfirst.test.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by kamau on 10/09/2018.
 */
public class OrderBookLevel {
    private OrderType side;
    private int count;
    private int quantity;
    private Float price;


    private List<Order> orders = new ArrayList<>();

    public OrderBookLevel() {

    }

    public OrderBookLevel(Order order) {
        this.side = order.getOrderType();
        this.count = 1;
        this.quantity = order.getAmount();
        this.price = order.getPrice();

        orders.add(order);
    }

    public void addOrder(Order order) {

        this.count++;
        this.quantity+=order.getAmount();
        this.price = order.getPrice();

        orders.add(order);
    }

    public void removeOrder(Order order) {
        this.count--;
        this.quantity-=order.getAmount();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderType getSide() {
        return side;
    }

    public void setSide(OrderType side) {
        this.side = side;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
