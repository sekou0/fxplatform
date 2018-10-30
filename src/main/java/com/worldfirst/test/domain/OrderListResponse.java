package com.worldfirst.test.domain;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class OrderListResponse extends ResourceSupport {

    private int total;

    private List<Order> allOrders;

    public OrderListResponse(List<Order> orders) {

        if(orders != null) {

            this.total = orders.size();
            this.allOrders = orders;
        } else {
            this.total = 0;
            allOrders = new ArrayList<>();
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Order> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(List<Order> allOrders) {
        this.allOrders = allOrders;
    }
}
