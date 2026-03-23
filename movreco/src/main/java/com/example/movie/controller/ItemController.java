package com.example.movie.controller;

import com.example.movie.service.ItemService;
import com.example.movie.service.ReviewService;
import com.example.movie.service.BookmarkService;
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
    private final BookmarkService bookmarkService;

    @GetMapping("/movies")
    public String movies(Model model, jakarta.servlet.http.HttpServletRequest request) {
        model.addAttribute("title", "Movies - Movreco");
        model.addAttribute("pageTitle", "영화 🎬");
        model.addAttribute("items", itemService.getItemsByType("MOVIE"));
        model.addAttribute("currentPath", request.getRequestURI());
        return "items";
    }

    @GetMapping("/books")
    public String books(Model model, jakarta.servlet.http.HttpServletRequest request) {
        model.addAttribute("title", "Books - Movreco");
        model.addAttribute("pageTitle", "도서 📚");
        model.addAttribute("items", itemService.getItemsByType("BOOK"));
        model.addAttribute("currentPath", request.getRequestURI());
        return "items";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if(keyword == null || keyword.trim().isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("title", "'" + keyword + "' 검색 결과");
        model.addAttribute("pageTitle", "'" + keyword + "' 검색 결과 🔍");
        model.addAttribute("items", itemService.searchItems(keyword));
        return "items";
    }

    @GetMapping("/items/{id}")
    public String itemDetail(@PathVariable("id") Long id, Model model, Principal principal) {
        model.addAttribute("item", itemService.getItemById(id));
        model.addAttribute("reviews", reviewService.getReviewsByItemId(id));
        boolean isBookmarked = principal != null && bookmarkService.isBookmarked(principal.getName(), id);
        model.addAttribute("isBookmarked", isBookmarked);
        return "item-detail";
    }

    @PostMapping("/items/{id}/bookmark")
    public String toggleBookmark(@PathVariable("id") Long id, Principal principal) {
        if (principal != null) {
            bookmarkService.toggleBookmark(principal.getName(), id);
        }
        return "redirect:/items/" + id;
    }

    @PostMapping("/items/{id}/reviews")
    public String addReview(@PathVariable("id") Long id, 
                            @RequestParam("content") String content,
                            @RequestParam("rating") Double rating,
                            Principal principal) {
        String username = principal != null ? principal.getName() : "user";
        reviewService.addReview(id, username, content, rating);
        return "redirect:/items/" + id;
    }

    @PostMapping("/items/{itemId}/reviews/{reviewId}/delete")
    public String deleteReview(@PathVariable("itemId") Long itemId, @PathVariable("reviewId") Long reviewId, Principal principal) {
        if (principal != null) {
            reviewService.deleteReview(reviewId, principal.getName());
        }
        return "redirect:/items/" + itemId;
    }
}
