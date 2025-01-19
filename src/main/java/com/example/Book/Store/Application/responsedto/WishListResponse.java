package com.example.Book.Store.Application.responsedto;

import com.example.Book.Store.Application.entity.Wishlist;

public class WishListResponse {
    private String message;
    private Long userId;
    private Integer  bookId;

    public WishListResponse(String message, long userId, Integer bookId) {
        this.message=message;
        this.bookId=bookId;
        this.userId=userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "WishListResponse{" +
                "message='" + message + '\'' +
                ", userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
