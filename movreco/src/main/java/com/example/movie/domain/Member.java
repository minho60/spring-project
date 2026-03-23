package com.example.movie.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Member {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
}
