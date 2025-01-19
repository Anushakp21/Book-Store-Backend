package com.example.Book.Store.Application.Handler;

public class OrderNotFoundByIdException extends RuntimeException{
    public OrderNotFoundByIdException(String message)
    {
        super(message);
    }
}
