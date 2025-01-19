package com.example.Book.Store.Application.Handler;

import com.example.Book.Store.Application.entity.Wishlist;

public class WishlistIdNotFoundException extends RuntimeException{
    public WishlistIdNotFoundException(String message)
    {
        super(message);
    }
}
