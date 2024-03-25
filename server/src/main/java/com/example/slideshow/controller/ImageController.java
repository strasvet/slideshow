package com.example.slideshow.controller;

import com.example.slideshow.model.AddImageRequest;
import com.example.slideshow.model.AddImageResponse;
import com.example.slideshow.repository.entity.Image;
import com.example.slideshow.repository.ImageRepository;
import com.example.slideshow.service.ImageServiceInt;
import com.example.slideshow.service.ImageServiceIntImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
public class ImageController {

    private final ImageServiceInt imageService;

    public ImageController(ImageServiceInt imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "/addImage", method = RequestMethod.POST)
    public ResponseEntity<AddImageResponse> addImage(@RequestBody AddImageRequest request) {
        AddImageResponse response = imageService.addImage(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteImage/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteIMage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public ResponseEntity<List<AddImageResponse>> getImages() {
        List<AddImageResponse> response = imageService.getAllImages();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
