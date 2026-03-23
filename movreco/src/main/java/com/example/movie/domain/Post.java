package com.example.movie.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Data
public class Post {
    @Id
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private Integer viewCount;
    private LocalDateTime createdAt;
}
