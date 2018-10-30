package com.worldfirst.test.services;

import com.worldfirst.test.dao.OrdersDao;
import com.worldfirst.test.domain.*;
import com.worldfirst.test.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Order Handler Implementation
 * Created by kamau on 24/10/2018.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private Map<String, OrderBook> orderBookMap = new HashMap<>();

    @Autowired
    private OrdersDao ordersDao;

    private final static Object lock = new Object();

    public OrderServiceImpl() {

        orderBookMap.put("GBP", new OrderBook("GBP"));
        orderBookMap.put("USD", new OrderBook("USD"));
    }

    @Override
    public Order registerOrder(OrderRequest orderRequest) throws AddOrderException, BadOrderTypeException, BadCurrencyException, BadPriceException, BadAmountException {

        Order order;

        synchronized (lock) {
            try {
                OrderType type = OrderType.valueOf(orderRequest.getOrderType());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new BadOrderTypeException(orderRequest.getOrderType());
            }

            if(!"GBP".equalsIgnoreCase(orderRequest.getCurrency()) && !"USD".equalsIgnoreCase(orderRequest.getCurrency())) {
                throw new BadCurrencyException(orderRequest.getCurrency());
            }

            if(orderRequest.getPrice() < 0L) {
                throw new BadPriceException();
            }

            if(orderRequest.getAmount() < 0F) {
                throw  new BadAmountException();
            }

            try {
                order = ordersDao.addOrder(orderRequest.toOrder());

                OrderBook orderBook = orderBookMap.get(order.getCurrency());

                if(orderBook == null) {
                    orderBook = new OrderBook(order);
                } else {
                    OrderBookLevel orderBookLevel;

                    SortedMap<Float, OrderBookLevel> orderBookLevelMap = orderBook.getOrderBookLevels().get(order.getOrderType());

                    if (orderBookLevelMap == null) {
                        orderBookLevelMap = new TreeMap<>(new OrderBookLevelComparator(order.getOrderType()));

                        orderBookLevel = new OrderBookLevel(order);

                        orderBookLevelMap.put(order.getPrice(), orderBookLevel);

                        orderBook.getOrderBookLevels().put(order.getOrderType(), orderBookLevelMap);

                    } else {
                        orderBookLevel = orderBookLevelMap.get(order.getPrice());

                        if (orderBookLevel == null) {

                            orderBookLevel = new OrderBookLevel(order);
                            orderBookLevelMap.put(order.getPrice(), orderBookLevel);
                        } else {
                            orderBookLevel.addOrder(order);
                        }
                    }
                }

                System.out.print("done");


            } catch (Exception e) {

                throw new AddOrderException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            }
        }

        return order;
    }

    @Override
    public void cancelOrder(Long orderId) throws NoSuchOrderException {

        ordersDao.cancelOrder(orderId);

    }

    @Override
    public LiveOrderSummary getLiveOrderSummary() {

        return null;
    }

    @Override
    public MatchedTradesSummary getMatchedTradesSummary() {
        return null;
    }

    public List<Order> getAllOrders() {
        return ordersDao.getAllOrders();
    }

    public void setAllOrders(List<Order> allOrders) {
        this.ordersDao.setAllOrders(allOrders);
    }

    public Map<String, OrderBook> getOrderBookMap() {
        return orderBookMap;
    }

    public void setOrderBookMap(Map<String, OrderBook> orderBookMap) {
        this.orderBookMap = orderBookMap;
    }

    public Order getOrder(Long orderId) throws NoSuchOrderException {

        return ordersDao.getOrder(orderId);
    }
}
