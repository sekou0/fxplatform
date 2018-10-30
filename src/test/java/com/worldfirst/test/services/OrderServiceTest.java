package com.worldfirst.test.services;

import com.worldfirst.test.dao.OrdersDao;
import com.worldfirst.test.dao.OrdersDaoImpl;
import com.worldfirst.test.domain.Order;
import com.worldfirst.test.domain.OrderRequest;
import com.worldfirst.test.domain.OrderType;
import com.worldfirst.test.exceptions.*;
import com.worldfirst.test.mocks.MockOrderData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Unit tests for Order Handler
 * Created by kamau on 24/10/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {


    @Mock
    private OrdersDao ordersDao;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllOrders() throws Exception {

        when(ordersDao.getAllOrders()).thenReturn(MockOrderData.getOrders());

        List<Order>  allOrders = orderService.getAllOrders();

        assertNotNull(allOrders);
        assertEquals(3, allOrders.size());

    }


    @Test
    public void registerOrder() throws Exception {

        when(ordersDao.addOrder(any(Order.class))).thenReturn(MockOrderData.getOrder());

        Order order = orderService.registerOrder(MockOrderData.getOrderRequest());
        assertNotNull(order);
        assertEquals(100, order.getOrderId().longValue());
    }

    @Test(expected = BadCurrencyException.class)
    public void registerOrderBadCurrency() throws Exception {
        when(ordersDao.addOrder(any(Order.class))).thenReturn(MockOrderData.getOrder());

        OrderRequest orderRequest = MockOrderData.getOrderRequest();
        orderRequest.setCurrency("XXX");

        orderService.registerOrder(orderRequest);
    }

    @Test(expected = BadOrderTypeException.class)
    public void registerOrderBadOrdertype() throws Exception {
        when(ordersDao.addOrder(any(Order.class))).thenReturn(MockOrderData.getOrder());

        OrderRequest orderRequest = MockOrderData.getOrderRequest();
        orderRequest.setOrderType("XXX");

        orderService.registerOrder(orderRequest);
    }

    @Test(expected = BadPriceException.class)
    public void registerOrderBadPrice() throws Exception {
        when(ordersDao.addOrder(any(Order.class))).thenReturn(MockOrderData.getOrder());

        OrderRequest orderRequest = MockOrderData.getOrderRequest();
        orderRequest.setPrice(-5F);

        orderService.registerOrder(orderRequest);
    }

    @Test(expected = BadAmountException.class)
    public void registerOrderBadAmount() throws Exception {
        when(ordersDao.addOrder(any(Order.class))).thenReturn(MockOrderData.getOrder());

        OrderRequest orderRequest = MockOrderData.getOrderRequest();
        orderRequest.setAmount(-20);

        orderService.registerOrder(orderRequest);
    }


    @Test(expected = BadOrderTypeException.class)
    public void registerOrderBadBuySell() throws Exception {
        when(ordersDao.addOrder(any(Order.class))).thenReturn(MockOrderData.getOrder());

        OrderRequest orderRequest = MockOrderData.getOrderRequest();
        orderRequest.setOrderType("XXX");

        orderService.registerOrder(orderRequest);
    }

    @Test(expected = NoSuchOrderException.class)
    public void cancelOrder() throws Exception {

        when(ordersDao.getOrder(100L)).thenReturn(MockOrderData.getOrder());

        ordersDao.getAllOrders().addAll(MockOrderData.getOrders());

        Order order = orderService.getOrder(100L);

        assertNotNull(order);
        assertEquals(100, order.getOrderId().longValue());

        orderService.cancelOrder(order.getOrderId());

        when(ordersDao.getOrder(1L)).thenThrow(NoSuchOrderException.class);

        order = orderService.getOrder(1L);
    }

    @Test(expected = NoSuchOrderException.class)
    public void cancelOrderBadOrderId() throws Exception {

        when(ordersDao.getAllOrders()).thenReturn(MockOrderData.getOrders());

        doThrow(NoSuchOrderException.class).when(ordersDao).cancelOrder(2000L);

        List<Order>  allOrders = orderService.getAllOrders();

        assertNotNull(allOrders);
        assertEquals(3, allOrders.size());

        orderService.cancelOrder(2000L);
    }

    @Test
    public void getLiveOrderSummary() throws Exception {
        throw new NotImplementedException();
    }

    @Test
    public void getMatchedTradesSummary() throws Exception {
        throw new NotImplementedException();
    }

}