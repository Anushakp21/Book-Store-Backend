package com.example.Book.Store.Application.serviceimpl;

import com.example.Book.Store.Application.Handler.BookNotFoundByIdException;
import com.example.Book.Store.Application.Handler.InvalidRequestException;
import com.example.Book.Store.Application.Handler.UserNotFoundByIdException;
import com.example.Book.Store.Application.Handler.WishlistIdNotFoundException;
import com.example.Book.Store.Application.entity.Book;
import com.example.Book.Store.Application.entity.Cart;
import com.example.Book.Store.Application.entity.User;
import com.example.Book.Store.Application.entity.Wishlist;
import com.example.Book.Store.Application.repository.BookRepository;
import com.example.Book.Store.Application.repository.CartRepository;
import com.example.Book.Store.Application.repository.UserRepository;
import com.example.Book.Store.Application.repository.WishlistRepository;
import com.example.Book.Store.Application.responsedto.WishListResponse;
import com.example.Book.Store.Application.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public WishListResponse addBookToWishList(long userId, Integer bookId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new  UserNotFoundByIdException("User Not Found"));
        Book book=bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundByIdException("Book Not Found"));

        Wishlist wishList= wishlistRepository.findByUserId(userId).orElse(new Wishlist());
        wishList.setUser(user);
        wishList.setBook(book);

        wishlistRepository.save(wishList);
        return new WishListResponse("Added To WishList",userId,bookId);
    }

    @Override
    public WishListResponse cancelWishList(long userId, Integer bookId) {
       User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundByIdException("User Not Found "));
       Book book=bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundByIdException("Book Not Found "));

       Wishlist wishlist=wishlistRepository.findByUserIdandBookId(userId,bookId).orElseThrow(()-> new WishlistIdNotFoundException("WishList Not Found"));
       wishlistRepository.delete(wishlist);
        return new WishListResponse("Remove From WishList",userId,bookId);
    }

    @Override
    public WishListResponse addWishListToCart(long userId, Integer bookId, Long quantity) {
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
        return new WishListResponse("Successfully added To Cart",userId,bookId);
    }

    @Override
    public List<WishListResponse> getAllItems(long userId) {

      User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundByIdException("User not Found"+userId));
      List<Wishlist> wishlists=wishlistRepository.findAll();
        return wishlists.stream()
                .map(item -> new WishListResponse("Wishlist item retrieved", item.getUser().getUserId(), item.getBook().getBookId()))
                .toList();
    }
}
