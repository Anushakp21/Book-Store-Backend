package com.example.Book.Store.Application.requestdto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRequest {

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Rating cannot be null")
    private Double rating;

    private  Long userId;
    private Integer bookId;
}
