package com.example.slideshow.service;

import com.example.slideshow.model.AddImageRequest;
import com.example.slideshow.model.AddImageResponse;

import java.util.List;

public interface ImageServiceInt {

    AddImageResponse addImage(AddImageRequest request);

    Boolean deleteIMage(Long imageId);

    List<AddImageResponse> getAllImages();

}
