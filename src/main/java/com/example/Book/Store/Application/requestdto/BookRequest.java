package com.example.Book.Store.Application.requestdto;

import com.example.Book.Store.Application.entity.Image;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BookRequest {
    @NotBlank(message = "Book Name cannot be blank")
    private String bookName;
    @NotBlank(message = "Author Name cannot be blank")
    private String authorName;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "Price is mandatory")
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;
    @NotNull(message = "Logo is mandatory")
    private Image logo;
    @NotNull(message = "Quantity cannot be blank")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Long quantity;

    public @NotBlank(message = "Book Name cannot be blank") String getBookName() {
        return bookName;
    }

    public void setBookName(@NotBlank(message = "Book Name cannot be blank") String bookName) {
        this.bookName = bookName;
    }

    public @NotBlank(message = "Author Name cannot be blank") String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(@NotBlank(message = "Author Name cannot be blank") String authorName) {
        this.authorName = authorName;
    }

    public @NotBlank(message = "Description is mandatory") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description is mandatory") String description) {
        this.description = description;
    }

    public @NotNull(message = "Price is mandatory") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price is mandatory") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0") Double price) {
        this.price = price;
    }

    public @NotNull(message = "Logo is mandatory") Image getLogo() {
        return logo;
    }

    public void setLogo(@NotNull(message = "Logo is mandatory") Image logo) {
        this.logo = logo;
    }

    public @NotNull(message = "Quantity cannot be blank") @Min(value = 1, message = "Quantity must be at least 1") Long getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = "Quantity cannot be blank") @Min(value = 1, message = "Quantity must be at least 1") Long quantity) {
        this.quantity = quantity;
    }
}
