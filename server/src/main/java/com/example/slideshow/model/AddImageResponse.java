package com.example.slideshow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AddImageResponse {
    private Long id;
    private Integer duration;
    private String url;
}
