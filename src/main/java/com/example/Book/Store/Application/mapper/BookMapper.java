package com.example.Book.Store.Application.mapper;

import com.example.Book.Store.Application.entity.Book;
import com.example.Book.Store.Application.entity.Image;
import com.example.Book.Store.Application.requestdto.BookRequest;
import com.example.Book.Store.Application.responsedto.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book mapToBook(BookRequest bookRequest) {
        Book book=new Book();
        book.setBookName(bookRequest.getBookName());
        book.setAuthorName(bookRequest.getAuthorName());
        book.setDescription(bookRequest.getDescription());
        book.setQuantity(bookRequest.getQuantity());
        book.setImages(bookRequest.getLogo());
        book.setPrice(bookRequest.getPrice());
        return book;
    }

    public BookResponse mapToBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(book.getBookId());
        bookResponse.setBookName(book.getBookName());
        bookResponse.setAuthorName(book.getAuthorName());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setQuantity(book.getQuantity());
        bookResponse.setBookImage(book.getImages());


        return bookResponse;
    }
}
