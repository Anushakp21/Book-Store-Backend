package com.example.Book.Store.Application.repository;

import com.example.Book.Store.Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    Optional<User> findBySetResetToken(String otp);

    Optional<User> findByEmail(String email);
}
