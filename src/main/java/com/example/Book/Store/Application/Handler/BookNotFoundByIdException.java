package com.example.Book.Store.Application.Handler;

public class BookNotFoundByIdException extends  RuntimeException{
   public BookNotFoundByIdException(String message)
   {
       super(message);
   }
}
