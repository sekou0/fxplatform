package com.worldfirst.test.dao;


import com.worldfirst.test.domain.Order;
import com.worldfirst.test.exceptions.NoSuchOrderException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrdersDaoImpl implements OrdersDao {

    private Float currentPrice;

    private List<Order> allOrders = new ArrayList<>();

    private Long orderId = 1L;

    public Float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public List<Order> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(List<Order> allOrders) {
        this.allOrders = allOrders;
    }

    public Order addOrder(Order newOrder) throws Exception {

        newOrder.setOrderId(orderId++);

        allOrders.add(newOrder);

        return newOrder;
    }

    public void cancelOrder(Long orderId) throws NoSuchOrderException {

        Order delOrder = this.getOrder(orderId);

        allOrders.remove(delOrder);
    }

    public Order getOrder(Long orderId) throws NoSuchOrderException {

        Optional<Order> orderOpt = allOrders.stream().findFirst().filter(order -> order.getOrderId() == orderId);

        if(orderOpt.isPresent()) {
            return orderOpt.get();
        } else {
            throw new NoSuchOrderException();
        }
    }
}
