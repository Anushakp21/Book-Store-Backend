package com.example.Book.Store.Application.controller;

import com.example.Book.Store.Application.entity.Image;
import com.example.Book.Store.Application.serviceimpl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<?> addImages(@RequestPart MultipartFile image) throws IOException {
        Image image1 = imageService.addImage(image);
        return new ResponseEntity<>(image1, HttpStatus.CREATED);
    }
}
