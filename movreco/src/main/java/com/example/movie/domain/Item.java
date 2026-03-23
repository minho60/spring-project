package com.example.movie.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Item {
    @Id
    private Long id;
    private String title;
    private String description;
    private String type; // MOVIE or BOOK
    private String imageUrl;
    private Double rating;
    private String genre;
}
