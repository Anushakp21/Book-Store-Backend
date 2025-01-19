package com.example.Book.Store.Application.service;

import com.example.Book.Store.Application.entity.Book;
import com.example.Book.Store.Application.requestdto.BookRequest;
import com.example.Book.Store.Application.responsedto.BookResponse;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {

    BookResponse addBook(MultipartFile bookImage, BookRequest bookRequest);

    BookResponse getBookById(int bookId);

    List<BookResponse> getAllBooks();

    BookResponse deleteBookById(int bookId);

    BookResponse changeBookPrice(int bookId, double price);

    BookResponse changeBookQuantity(int bookId, long quantity);

    BookResponse addBook(Book book);
}
