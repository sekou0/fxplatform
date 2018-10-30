package com.worldfirst.test.mocks;

import com.worldfirst.test.domain.Order;
import com.worldfirst.test.domain.OrderRequest;
import com.worldfirst.test.domain.OrderType;

import java.util.ArrayList;
import java.util.List;

public class MockOrderData {

    static public Order getOrder() {

        Order order = new Order();

        order.setOrderId(100L);
        order.setAmount(3000);
        order.setCurrency("GBP");
        order.setOrderType(OrderType.BID);
        order.setPrice(2.000F);
        order.setUserId(3030L);

        return order;


    }

    static public OrderRequest getOrderRequest() {

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setAmount(3000);
        orderRequest.setCurrency("GBP");
        orderRequest.setOrderType("BID");
        orderRequest.setPrice(2.000F);
        orderRequest.setUserId(3030L);

        return orderRequest;

    }

    static public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        Order order = new Order();
        order.setOrderId(1L);
        order.setAmount(2000);
        order.setCurrency("GBP");
        order.setPrice((float)1.2100);
        order.setUserId(1000L);
        order.setOrderType(OrderType.ASK);

        orders.add(order);

        order = new Order();
        order.setOrderId(2L);
        order.setAmount(3000);
        order.setCurrency("GBP");
        order.setPrice((float)1.800);
        order.setUserId(1000L);
        order.setOrderType(OrderType.ASK);

        orders.add(order);

        order = new Order();
        order.setOrderId(3L);
        order.setAmount(4000);
        order.setCurrency("GBP");
        order.setPrice((float)1.100);
        order.setUserId(2000L);
        order.setOrderType(OrderType.BID);

        orders.add(order);

        return  orders;
    }
}
