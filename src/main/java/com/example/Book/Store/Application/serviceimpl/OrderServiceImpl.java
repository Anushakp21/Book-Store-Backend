package com.example.Book.Store.Application.serviceimpl;

import com.example.Book.Store.Application.Handler.InvalidQuantityException;
import com.example.Book.Store.Application.Handler.OrderNotFoundByIdException;
import com.example.Book.Store.Application.Handler.UserNotFoundByIdException;
import com.example.Book.Store.Application.entity.*;
import com.example.Book.Store.Application.mapper.OrderMapper;
import com.example.Book.Store.Application.repository.CartRepository;
import com.example.Book.Store.Application.repository.OrderRepository;
import com.example.Book.Store.Application.repository.UserRepository;
import com.example.Book.Store.Application.requestdto.OrderRequest;
import com.example.Book.Store.Application.responsedto.OrderResponse;
import com.example.Book.Store.Application.security.Util;
import com.example.Book.Store.Application.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    Util util;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartServiceImpl cartService;

    @Autowired
    BookServiceImpl bookService;

    @Transactional
    public OrderResponse placeOrder(String token,OrderRequest orderRequest) {
        User user=fetchUserByToken(token);

        List<Cart> carts= cartService.findAllByUserId(user.getUserId());

        double totalPrice=carts.stream().mapToDouble(
                cart ->{
                    Book book=cart.getBook();
                    if(cart.getQuantity() > book.getQuantity())
                    {
                            throw  new InvalidQuantityException("Not enough stock for book: " + book.getBookName()+ " in cart " + cart.getCartId());
                    }
                return cart.getTotalPrice();
                }
        ).sum();

        Order order=new Order();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setPrice(totalPrice);
        System.out.println(orderRequest.getAddress());
        order.setAddress(orderRequest.getAddress());
        order.setCancel(false);

        List<OrderBook> orderBooks = carts.stream().map(cart -> {
            Book book = cart.getBook();
            book.setQuantity(book.getQuantity() - cart.getQuantity());
            bookService.updateBook(book);

            order.setQuantity(cart.getQuantity());

            OrderBook orderBook = new OrderBook();
            orderBook.setOrder(order);
            orderBook.setBook(book);
            orderBook.setQuantity(cart.getQuantity());
            return orderBook;
        }).toList();

        order.setOrderBooks(orderBooks);

        Order savedOrder = orderRepository.save(order);

        cartService.removeFromCartByUserId(user.getUserId());

        return orderMapper.mapToOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse cancelOrder(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundByIdException("Order not found"));

        if (order.isCancel()) {
            throw new RuntimeException("Order is already canceled");
        }

        order.getOrderBooks().forEach(orderBook -> {
            Book book = orderBook.getBook();
            book.setQuantity(book.getQuantity() + orderBook.getQuantity());
            bookService.updateBook(book);
        });

        order.setCancel(true);
        orderRepository.save(order);

        return orderMapper.mapToOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::mapToOrderResponse)
                .toList();
    }

    @Override
    public List<OrderResponse> getAllOrdersForUser(String token) {
        long userId=util.extractUserIdFromToken(token);
            return orderRepository.findByUserId(userId).stream().
                    map(orderMapper::mapToOrderResponse).toList();
    }

    private User fetchUserByToken(String token) {
        long userId=util.extractUserIdFromToken(token);
        return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundByIdException("User Not Found"));
    }
}
