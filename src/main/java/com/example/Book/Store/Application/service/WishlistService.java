package com.example.Book.Store.Application.service;

import com.example.Book.Store.Application.responsedto.WishListResponse;

import java.util.List;

public interface WishlistService {
    WishListResponse addBookToWishList(long userId, Integer bookId);

    WishListResponse cancelWishList(long userId, Integer bookId);

    WishListResponse addWishListToCart(long userId, Integer bookId, Long quantity);

    List<WishListResponse> getAllItems(long userId);
}
