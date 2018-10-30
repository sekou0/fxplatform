package com.worldfirst.test.dao;

import com.worldfirst.test.domain.Order;
import com.worldfirst.test.exceptions.NoSuchOrderException;

import java.util.List;

public interface OrdersDao {

    Float getCurrentPrice();

    void setCurrentPrice(Float currentPrice);

    List<Order> getAllOrders();

    void setAllOrders(List<Order> allOrders);

    Order addOrder(Order newOrder) throws Exception;

    void cancelOrder(Long orderId) throws NoSuchOrderException;

    Order getOrder(Long orderId) throws NoSuchOrderException;


}
