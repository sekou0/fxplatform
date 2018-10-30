package com.worldfirst.test.controllers;

import com.worldfirst.test.domain.*;
import com.worldfirst.test.exceptions.*;
import com.worldfirst.test.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OrderListResponse> getAllOrders() {

        List<Order> orders = orderService.getAllOrders();

        OrderListResponse response = new OrderListResponse(orders);

        response.getLinks().add(linkTo(methodOn(OrderController.class).getAllOrders()).withSelfRel());

        return ResponseEntity.ok(response);
    }


    @RequestMapping(method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Order> addOrder(
            @RequestBody(required = true)OrderRequest orderRequest
            ) throws AddOrderException, BadCurrencyException, BadOrderTypeException, BadAmountException, BadPriceException {

        Order order = orderService.registerOrder(orderRequest);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);

    }

    @RequestMapping(path = "/{orderId}", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Order> getOrder(
            @PathVariable Long orderId) throws NoSuchOrderException {

        Order order = orderService.getOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @RequestMapping(path = "/live", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LiveOrderSummary> getLiveOrders() {

        LiveOrderSummary liveOrderSummary = new LiveOrderSummary();


        return ResponseEntity.ok(liveOrderSummary);
    }

    @RequestMapping(path = "/matched", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MatchedTradesSummary> getMatchedOrders() {

        MatchedTradesSummary matchedTradesSummary = orderService.getMatchedTradesSummary();

        return ResponseEntity.ok(matchedTradesSummary);
    }

    @ExceptionHandler(AddOrderException.class)
    public ResponseEntity<ErrorResponse> throwError(AddOrderException ex) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setHttpCode(ex.getHttpCode());
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(NoSuchOrderException.class)
    public ResponseEntity<ErrorResponse> badRequestError(NoSuchOrderException ex) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setHttpCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({BadCurrencyException.class, BadOrderTypeException.class})
    public ResponseEntity<ErrorResponse> badReqestException(Exception ex) {

        return ResponseEntity.badRequest().build();
    }
}
