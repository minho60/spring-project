package com.example.movie.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Data
public class Comment {
    @Id
    private Long id;
    private Long postId;
    private Long memberId;
    private String content;
    private LocalDateTime createdAt;
}
