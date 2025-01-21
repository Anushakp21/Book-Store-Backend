package com.example.Book.Store.Application.serviceimpl;

import com.example.Book.Store.Application.entity.Image;
import com.example.Book.Store.Application.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image addImage(MultipartFile image) throws  IOException {
        Image imageData = new Image();
        imageData.setImageName(image.getOriginalFilename());
        imageData.setImageType(image.getContentType());
        imageData.setImageData(image.getBytes());
        System.out.println(imageData.getImageName());

        return imageRepository.save(imageData);
    }
}
