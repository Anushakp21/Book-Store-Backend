package com.example.Book.Store.Application.mapper;

import com.example.Book.Store.Application.entity.Cart;
import com.example.Book.Store.Application.responsedto.CartResponse;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartResponse mapToCartResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartId(cart.getCartId());
        cartResponse.setBookId(cart.getBook().getBookId());
        cartResponse.setUserId(cart.getUser().getUserId());
        cartResponse.setQuantity(cart.getQuantity());
        cartResponse.setTotalPrice(cart.getTotalPrice());
        return cartResponse;
    }
}
