package com.example.slideshow;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SlideShow API", version = "0.0.1", description = "SlideShow backend api"))
public class SlideshowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlideshowApplication.class, args);
    }
}
