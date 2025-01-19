package com.example.Book.Store.Application.service;

import com.example.Book.Store.Application.requestdto.OrderRequest;
import com.example.Book.Store.Application.responsedto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(String token,OrderRequest orderRequest);

    OrderResponse cancelOrder(long orderId);

    List<OrderResponse> getAllOrders();

    List<OrderResponse> getAllOrdersForUser(String token);
}
