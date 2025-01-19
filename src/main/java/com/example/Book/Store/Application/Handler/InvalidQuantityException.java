package com.example.Book.Store.Application.Handler;

public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException(String message)
    {
        super(message);
    }
}
