package com.example.Book.Store.Application.Handler;

public class InvalidResetTokenException extends  RuntimeException{
    public  InvalidResetTokenException(String message)
    {
        super(message);
    }
}
