package com.example.Book.Store.Application.Handler;

public class InvalidRequestException extends  RuntimeException{
    public InvalidRequestException(String message)
    {
        super(message);
    }
}
