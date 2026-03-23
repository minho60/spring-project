package com.example.movie.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String username;
    private String content;
    private Double rating;
    private LocalDateTime createdAt;
}
