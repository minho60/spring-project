package com.example.movie.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Data
public class Review {
    @Id
    private Long id;
    private Long itemId;
    private Long memberId;
    private String content;
    private Double rating;
    private LocalDateTime createdAt;
}
