package com.example.movie.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String username;
    private String nickname;
    private String title;
    private String content;
    private Integer viewCount;
    private LocalDateTime createdAt;
}
