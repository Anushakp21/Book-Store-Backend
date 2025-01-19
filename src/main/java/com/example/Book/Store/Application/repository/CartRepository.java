package com.example.Book.Store.Application.repository;

import com.example.Book.Store.Application.entity.Book;
import com.example.Book.Store.Application.entity.Cart;
import com.example.Book.Store.Application.entity.User;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserAndBook(User user, Book book);
    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    List<Cart> findAllByUserId(long userId);
}
