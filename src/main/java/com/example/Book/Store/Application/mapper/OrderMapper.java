package com.example.Book.Store.Application.mapper;

import com.example.Book.Store.Application.entity.Order;
import com.example.Book.Store.Application.responsedto.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse mapToOrderResponse(Order order)
    {
        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setPrice(order.getPrice());
        orderResponse.setAddress(order.getAddress());
        orderResponse.setUserId(order.getUser().getUserId());
        orderResponse.setQuantity(order.getQuantity());
        return orderResponse;
    }
}
