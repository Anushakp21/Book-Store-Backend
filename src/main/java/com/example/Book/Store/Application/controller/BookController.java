package com.example.Book.Store.Application.controller;

import com.example.Book.Store.Application.entity.Book;
import com.example.Book.Store.Application.entity.Image;
import com.example.Book.Store.Application.mapper.BookMapper;
import com.example.Book.Store.Application.requestdto.BookRequest;
import com.example.Book.Store.Application.responsedto.BookResponse;
import com.example.Book.Store.Application.security.Util;
import com.example.Book.Store.Application.serviceimpl.BookServiceImpl;
import com.example.Book.Store.Application.serviceimpl.ImageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@RestController
@RequestMapping("/admin")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class BookController {

    private final BookServiceImpl bookService;
    private final ImageService imageService;
    private final BookMapper bookMapper;
    private final Util util;

    public BookController(BookServiceImpl bookService,
                          ImageService imageService,
                          BookMapper bookMapper,
                          Util util) {
        this.bookService = bookService;
        this.imageService = imageService;
        this.bookMapper = bookMapper;
        this.util = util;
    }
    @PostMapping(value = "/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BookResponse> addBook(  @RequestPart("bookImage") MultipartFile bookImage,
                                                  @RequestPart("bookRequest") String bookRequestJson) throws IOException {

        // Convert JSON string to BookRequest object
        ObjectMapper objectMapper = new ObjectMapper();
        BookRequest bookRequest = objectMapper.readValue(bookRequestJson, BookRequest.class);
        Image savedImage = imageService.addImage(bookImage);
        Book book = bookMapper.mapToBook(bookRequest);
        savedImage.setBook(book);
        book.setImages(savedImage);
        BookResponse bookResponse = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable int bookId) {
        BookResponse bookResponse = bookService.getBookById(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getAllBooks(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        long userId = util.extractUserIdFromToken(token);
        List<BookResponse> bookResponses = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(bookResponses);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<BookResponse> deleteBookById(@PathVariable int bookId) {
        BookResponse bookResponse = bookService.deleteBookById(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @PatchMapping("/books/{bookId}/price")
    public ResponseEntity<BookResponse> changeBookPrice(@PathVariable int bookId, @RequestParam double price) {
        BookResponse bookResponse = bookService.changeBookPrice(bookId,price);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookResponse);
    }

    @PatchMapping("/books/{bookId}/quantity")
    public ResponseEntity<BookResponse> changeBookQuantity(@PathVariable int bookId, @RequestParam long quantity) {
        BookResponse bookResponse = bookService.changeBookQuantity(bookId,quantity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookResponse);
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build(); // Return HTTP 200 OK for preflight requests
    }
}
