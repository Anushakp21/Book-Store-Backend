package com.example.Book.Store.Application.responsedto;

import com.example.Book.Store.Application.entity.Address;

import java.time.LocalDate;

public class OrderResponse {
    private long orderId;
    private LocalDate orderDate;
    private double price;
    private long quantity;
    private long userId;
    private Address address;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", price=" + price +
                ", quantity=" + quantity +
                ", userId=" + userId +
                ", address=" + address +
                '}';
    }
}
