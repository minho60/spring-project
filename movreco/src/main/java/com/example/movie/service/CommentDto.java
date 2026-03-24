package com.example.movie.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Long postId;
    private String username;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
}
