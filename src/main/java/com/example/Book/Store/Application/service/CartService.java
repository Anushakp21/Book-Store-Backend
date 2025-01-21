package com.example.Book.Store.Application.service;

import com.example.Book.Store.Application.responsedto.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse addToCart(long userId, Integer bookId, Long quantity);

    CartResponse updateQuantity(long cartId, long quantity);

    CartResponse removeFromCartByCartId(long cartId);

    List<CartResponse> removeFromCartByUserId(long userId);

    List<CartResponse> getAllCartItemsForUser(long userId);

    List<CartResponse> getAllCartItems();
}
