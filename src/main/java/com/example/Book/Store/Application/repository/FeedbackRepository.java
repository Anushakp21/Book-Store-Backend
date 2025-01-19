package com.example.Book.Store.Application.repository;

import com.example.Book.Store.Application.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> findByUserUserId(Long userId);
}
