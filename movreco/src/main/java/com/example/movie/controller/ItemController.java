package com.example.movie.controller;

import com.example.movie.service.ItemService;
import com.example.movie.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ReviewService reviewService;

    @GetMapping("/movies")
    public String movies(Model model, jakarta.servlet.http.HttpServletRequest request) {
        model.addAttribute("title", "Movies - Movreco");
        model.addAttribute("pageTitle", "Explore Movies 🎬");
        model.addAttribute("items", itemService.getItemsByType("MOVIE"));
        model.addAttribute("currentPath", request.getRequestURI()); // 🔥 추가
        return "items";
    }

    @GetMapping("/books")
    public String books(Model model, jakarta.servlet.http.HttpServletRequest request) {
        model.addAttribute("title", "Books - Movreco");
        model.addAttribute("pageTitle", "Explore Books 📚");
        model.addAttribute("items", itemService.getItemsByType("BOOK"));
        model.addAttribute("currentPath", request.getRequestURI()); // 🔥 추가
        return "items";
    }

    @GetMapping("/items/{id}")
    public String itemDetail(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        model.addAttribute("reviews", reviewService.getReviewsByItemId(id));
        return "item-detail";
    }

    @PostMapping("/items/{id}/reviews")
    public String addReview(@PathVariable Long id, 
                            @RequestParam String content,
                            @RequestParam Double rating,
                            Principal principal) {
        String username = principal != null ? principal.getName() : "user";
        reviewService.addReview(id, username, content, rating);
        return "redirect:/items/" + id;
    }
}
