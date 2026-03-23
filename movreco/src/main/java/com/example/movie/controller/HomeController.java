package com.example.movie.controller;

import com.example.movie.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemService itemService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Movreco - The Best Recommendations");
        model.addAttribute("items", itemService.getAllItems());
        return "home";
    }
}
