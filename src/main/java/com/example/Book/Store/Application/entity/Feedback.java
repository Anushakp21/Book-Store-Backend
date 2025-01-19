package com.example.Book.Store.Application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;
    private String description;
    private Double rating;

    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
}
