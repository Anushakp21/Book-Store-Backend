package com.example.Book.Store.Application.controller;

import com.example.Book.Store.Application.responsedto.CartResponse;
import com.example.Book.Store.Application.responsedto.WishListResponse;
import com.example.Book.Store.Application.security.Util;
import com.example.Book.Store.Application.serviceimpl.WishlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishListController {

    @Autowired
    Util util;

    @Autowired
    WishlistServiceImpl wishListService;


    @PostMapping("wishlist/add/{bookId}")
    public ResponseEntity<WishListResponse> addToWishList(@RequestHeader ("Authorization") String authHeader,@PathVariable Integer bookId)
    {
       String token=authHeader.substring(7);
       long userId= util.extractUserIdFromToken(token);
       WishListResponse wishListResponse=wishListService.addBookToWishList(userId,bookId);
       return ResponseEntity.status(HttpStatus.CREATED).body(wishListResponse);
    }

    @DeleteMapping("wishlist/cancel/{bookId}")
    public ResponseEntity<WishListResponse> cancelWishList(@RequestHeader("Authorization")String authHeader,@PathVariable Integer bookId )
    {
        String token=authHeader.substring(7);
        long userId= util.extractUserIdFromToken(token);
        WishListResponse wishListResponse=wishListService.cancelWishList(userId,bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(wishListResponse);
    }
    @PostMapping("/wishlist/cartload")
    public ResponseEntity<WishListResponse> addToCart(@RequestHeader ("Authorization") String authHeader,@RequestParam Integer bookId,@RequestParam Long quantity)
    {
        String token = authHeader.substring(7);
        long userId = util.extractUserIdFromToken(token);
        WishListResponse wishListResponse = wishListService.addWishListToCart(userId, bookId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(wishListResponse);
    }

    @GetMapping("/wishlist/getAllItems")
    public ResponseEntity<List<WishListResponse>> getAllWishListItems(@RequestHeader("Authorization") String authHeader)
    {
        String token=authHeader.substring(7);
        long userId=util.extractUserIdFromToken(token);
        List<WishListResponse> wishListResponse=wishListService.getAllItems(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(wishListResponse);
    }
}
