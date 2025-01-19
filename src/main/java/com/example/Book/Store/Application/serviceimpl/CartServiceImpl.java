package com.example.Book.Store.Application.serviceimpl;

import com.example.Book.Store.Application.Handler.BookNotFoundByIdException;
import com.example.Book.Store.Application.Handler.CartNotFoundByIdException;
import com.example.Book.Store.Application.Handler.InvalidRequestException;
import com.example.Book.Store.Application.Handler.UserNotFoundByIdException;
import com.example.Book.Store.Application.entity.Book;
import com.example.Book.Store.Application.entity.Cart;
import com.example.Book.Store.Application.entity.User;
import com.example.Book.Store.Application.mapper.CartMapper;
import com.example.Book.Store.Application.repository.BookRepository;
import com.example.Book.Store.Application.repository.CartRepository;
import com.example.Book.Store.Application.repository.UserRepository;
import com.example.Book.Store.Application.responsedto.CartResponse;
import com.example.Book.Store.Application.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartMapper cartMapper;

    @Override
    public CartResponse addToCart(long userId, Integer bookId, Long quantity) {
        if(quantity <=0)
        {
            throw new InvalidRequestException("Quantity must be greater than zero");
        }

        User user=userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundByIdException("User not found with ID: " + userId));

        Book book=bookRepository.findById(bookId)
                .orElseThrow(()->new BookNotFoundByIdException("Book Not Available "+bookId));

        Cart cart = cartRepository.findByUserAndBook(user, book)
                .map(existingCart -> {
                    existingCart.setQuantity(existingCart.getQuantity() + quantity);
                    existingCart.setTotalPrice((long) (existingCart.getQuantity() * book.getPrice()));
                    return existingCart;
                }).orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setBook(book);
                    newCart.setQuantity(quantity);
                    newCart.setTotalPrice((long) (quantity * book.getPrice()));
                    return newCart;
                });

        cartRepository.save(cart);
        return cartMapper.mapToCartResponse(cart);
    }

    @Override
    public CartResponse updateQuantity(long cartId, long quantity) {
        if (quantity <= 0) {
            throw new InvalidRequestException("Quantity must be greater than zero");
        }
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.setQuantity(quantity);
                    cart = cartRepository.save(cart);
                    return cartMapper.mapToCartResponse(cart);
                }).orElseThrow(()->new CartNotFoundByIdException("Unable to update quantity"));
    }

    @Override
    public CartResponse removeFromCartByCartId(long cartId) {
        return cartRepository.findById(cartId)
                .map(cart->{
                    cartRepository.delete(cart);
                    return cartMapper.mapToCartResponse(cart);
                }).orElseThrow(()->new CartNotFoundByIdException("Unable to remove from cart"));
    }

    @Override
    public List<CartResponse> removeFromCartByUserId(long userId) {
        return cartRepository.findAllByUserId(userId)
                .stream()
                .map(cart -> {
                    cartRepository.delete(cart);
                    return cartMapper.mapToCartResponse(cart);
                }).toList();
    }

    @Override
    public List<CartResponse> getAllCartItemsForUser(long userId) {
        return cartRepository.findAllByUserId(userId)
                .stream()
                .map(cart -> cartMapper.mapToCartResponse(cart))
                .toList();
    }

    @Override
    public List<CartResponse> getAllCartItems() {
        return cartRepository.findAll()
                .stream().map(cart -> cartMapper.mapToCartResponse(cart))
                .toList();
    }

    public List<Cart> findAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }
}
