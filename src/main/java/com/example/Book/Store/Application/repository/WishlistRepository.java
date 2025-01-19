package com.example.Book.Store.Application.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.Book.Store.Application.entity.Book;
import com.example.Book.Store.Application.entity.User;
import com.example.Book.Store.Application.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

    @Query("SELECT w FROM Wishlist w WHERE w.user.id=:userId")
    Optional<Wishlist> findByUserId(@Param("userId") Long userId);

    @Query("SELECT w FROM Wishlist w WHERE w.user.id = :userId AND w.book.id = :bookId")
    Optional<Wishlist> findByUserIdandBookId(long userId, Integer bookId);


}
