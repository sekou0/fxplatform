package com.worldfirst.test.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldfirst.test.domain.OrderRequest;
import com.worldfirst.test.exceptions.AddOrderException;
import com.worldfirst.test.exceptions.NoSuchOrderException;
import com.worldfirst.test.mocks.MockOrderData;
import com.worldfirst.test.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {


    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

    }

    @Mock
    private OrderService orderService;


    @Test
    public void getAllOrders() throws Exception {

        when(orderService.getAllOrders()).thenReturn(MockOrderData.getOrders());

        mockMvc.perform(
                get("/orders/"))
                .andExpect(status().isOk())
                .andExpect(
                        content()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.total", is(3)));

    }

    @Test
    public void getOrder() throws Exception {

        when(orderService.getOrder(any(Long.class))).thenReturn(MockOrderData.getOrder());

        mockMvc.perform(
                get("/orders/100"))
                .andExpect(status().isOk())
                .andExpect(
                        content()
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.orderId", is(100)));

    }

    @Test
    public void getOrderBadOrderId() throws Exception {

        when(orderService.getOrder(any(Long.class))).thenThrow(NoSuchOrderException.class);

        mockMvc.perform(
                get("/orders/100"))
                .andExpect(status().isBadRequest())
                .andExpect(
                        content()
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.httpCode", is("400")));

    }

    @Test
    public void addOrderOk() throws Exception {

        OrderRequest orderRequest = MockOrderData.getOrderRequest();

        String orderRequestJson = getJsonString(orderRequest);

        when(orderService.registerOrder(any(OrderRequest.class))).thenReturn(MockOrderData.getOrder());

        mockMvc.perform(
                post("/orders")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderRequestJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.orderId", is(100)));
    }

    @Test
    public void addOrderBadAdd() throws Exception {

        OrderRequest orderRequest = MockOrderData.getOrderRequest();

        String orderRequestJson = getJsonString(orderRequest);

        when(orderService.registerOrder(any(OrderRequest.class))).thenThrow(new AddOrderException("500", "Server Error"));

        mockMvc.perform(
                post("/orders")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderRequestJson))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.httpCode", is("500")));

    }

    private String getJsonString(OrderRequest orderRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(orderRequest);
    }

}