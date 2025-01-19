package com.example.Book.Store.Application.Handler;

public class UserNotFoundByIdException extends RuntimeException{
    public UserNotFoundByIdException(String message)
    {
        super(message);
    }
}
