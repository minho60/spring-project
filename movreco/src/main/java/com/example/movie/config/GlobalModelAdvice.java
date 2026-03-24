package com.example.movie.config;

import com.example.movie.domain.Genre;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("genres")
    public Genre[] genres() {
        return Genre.values();
    }
}
