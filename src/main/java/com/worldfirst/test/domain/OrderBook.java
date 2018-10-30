package com.worldfirst.test.domain;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * Created by kamau on 24/10/2018.
 */
public class OrderBook {

    private String currency;

    private Float currentPrice;

    private Map<OrderType, SortedMap<Float, OrderBookLevel>> orderBookLevels;

    public OrderBook() {

        orderBookLevels = new TreeMap<>();

        orderBookLevels.put(OrderType.ASK, null);
        orderBookLevels.put(OrderType.BID, null);
    }

    public OrderBook(String currency) {

        this();
        this.currency = currency;
    }

    public OrderBook(Order order) {

        this.currency = order.getCurrency();

        SortedMap<Float, OrderBookLevel> orderBookLevelMap = new TreeMap<>(new OrderBookLevelComparator(order.getOrderType()));

        orderBookLevelMap.put(order.getPrice(), new OrderBookLevel(order));

        orderBookLevels.put(order.getOrderType(), orderBookLevelMap);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Map<OrderType, SortedMap<Float, OrderBookLevel>> getOrderBookLevels() {
        return orderBookLevels;
    }

    public void setOrderBookLevels(Map<OrderType, SortedMap<Float, OrderBookLevel>> orderBookLevels) {
        this.orderBookLevels = orderBookLevels;
    }
}
