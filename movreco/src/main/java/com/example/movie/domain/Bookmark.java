package com.example.movie.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Bookmark {
    @Id
    private Long id;
    private Long memberId;
    private Long itemId;
}
