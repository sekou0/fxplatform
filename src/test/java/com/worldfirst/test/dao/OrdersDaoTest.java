package com.worldfirst.test.dao;

import com.worldfirst.test.domain.Order;
import com.worldfirst.test.exceptions.NoSuchOrderException;
import com.worldfirst.test.mocks.MockOrderData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersDaoTest {

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void cleanUp() {
        ordersDao.getAllOrders().clear();
    }

    @Autowired
    private OrdersDao ordersDao;

    @Test
    public void getCurrentPrice() {

        Float currentPrice = 2.223F;

        ordersDao.setCurrentPrice(currentPrice);

        assertNotNull(ordersDao);
        assertEquals(currentPrice, ordersDao.getCurrentPrice());
    }

    @Test
    public void addOrder() throws Exception{
        assertNotNull(ordersDao);
        assertNotNull(ordersDao.getAllOrders());
        assertEquals(0, ordersDao.getAllOrders().size());

        ordersDao.addOrder(new Order());

        assertEquals(1, ordersDao.getAllOrders().size());
    }

    @Test
    public void cancelOrder() throws Exception {

        ordersDao.setAllOrders(MockOrderData.getOrders());

        assertEquals(3, ordersDao.getAllOrders().size());

        ordersDao.cancelOrder(1L);

        assertEquals(2, ordersDao.getAllOrders().size());

    }

    @Test(expected = NoSuchOrderException.class)
    public void cancelOrderBadId() throws Exception {

        ordersDao.setAllOrders(MockOrderData.getOrders());

        assertEquals(3, ordersDao.getAllOrders().size());

        ordersDao.cancelOrder(1000L);

    }

    @Test
    public void getOrder() throws Exception {

        ordersDao.setAllOrders(MockOrderData.getOrders());

        assertEquals(3, ordersDao.getAllOrders().size());

        Order order = ordersDao.getOrder(1L);

        assertNotNull(order);
        assertEquals(1L, order.getOrderId().longValue());


    }

    @Test(expected = NoSuchOrderException.class)
    public void getOrderBadId() throws Exception {

        ordersDao.setAllOrders(MockOrderData.getOrders());

        assertEquals(3, ordersDao.getAllOrders().size());

        Order order = ordersDao.getOrder(10000L);
    }


}