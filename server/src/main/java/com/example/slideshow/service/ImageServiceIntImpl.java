package com.example.slideshow.service;

import com.example.slideshow.model.AddImageRequest;
import com.example.slideshow.model.AddImageResponse;
import com.example.slideshow.repository.ImageRepository;
import com.example.slideshow.repository.entity.Image;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceIntImpl implements ImageServiceInt {

    private final ImageRepository imageRepository;

    public ImageServiceIntImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public AddImageResponse addImage(AddImageRequest request) {
        Image newImage = new Image(request.getUrl(), request.getDuration());
        Image savedImage = imageRepository.save(newImage);
        return AddImageResponse.builder()
                .id(savedImage.getId())
                .url(savedImage.getUrl())
                .duration(savedImage.getDuration())
                .build();
    }

    @Override
    public Boolean deleteIMage(Long imageId) {
        imageRepository.deleteById(imageId);
        return true;
    }

    @Override
    public List<AddImageResponse> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return images.stream().map(image -> AddImageResponse.builder()
                .id(image.getId())
                .duration(image.getDuration())
                .url(image.getUrl())
                .build()
        ).collect(Collectors.toList());
    }
}
