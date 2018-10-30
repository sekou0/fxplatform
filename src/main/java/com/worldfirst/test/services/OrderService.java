package com.worldfirst.test.services;

import com.worldfirst.test.domain.*;
import com.worldfirst.test.exceptions.*;

import java.util.List;
import java.util.Map;

/**
 * Interface for the Order Handler
 * Created by kamau on 24/10/2018.
 */
public interface OrderService {

    List<Order> getAllOrders();

    Order registerOrder(OrderRequest order) throws
            AddOrderException,
            BadOrderTypeException,
            BadCurrencyException,
            BadPriceException,
            BadAmountException;

    void cancelOrder(Long orderId) throws NoSuchOrderException;

    LiveOrderSummary getLiveOrderSummary();

    MatchedTradesSummary getMatchedTradesSummary();

    Order getOrder(Long orderId) throws NoSuchOrderException;

    Map<String, OrderBook> getOrderBookMap();

    void setOrderBookMap(Map<String, OrderBook> orderBookMap);
}
