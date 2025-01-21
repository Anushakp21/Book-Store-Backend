package com.example.Book.Store.Application.controller;

import com.example.Book.Store.Application.requestdto.OrderRequest;
import com.example.Book.Store.Application.responsedto.OrderResponse;
import com.example.Book.Store.Application.serviceimpl.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestHeader ("Authorization") String authHeader,@RequestBody @Valid   OrderRequest orderRequest)
    {
        String token=authHeader.substring(7);
        OrderResponse orderResponse=orderService.placeOrder(token,orderRequest);
        System.out.println(orderResponse.getAddress());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PatchMapping("/orders/{orderId}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@RequestHeader("Authorization") String authHeader, @PathVariable long orderId) {
        OrderResponse orderResponse = orderService.cancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderResponse);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orderResponses = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orderResponses);
    }

    @GetMapping("/orders/user")
    public ResponseEntity<List<OrderResponse>> getAllOrdersForUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        List<OrderResponse> orderResponses = orderService.getAllOrdersForUser(token);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponses);
    }
}
