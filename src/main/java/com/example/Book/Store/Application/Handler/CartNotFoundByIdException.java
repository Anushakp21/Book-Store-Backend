package com.example.Book.Store.Application.Handler;

public class CartNotFoundByIdException extends RuntimeException{
   public  CartNotFoundByIdException(String message)
    {
        super(message);
    }
}
