package com.example.movie.controller;

import com.example.movie.service.PostService;
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
public class PostController {

    private final PostService postService;

    @GetMapping("/board")
    public String board(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "board";
    }

    @GetMapping("/board/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "post-detail";
    }

    @GetMapping("/board/new")
    public String newPostForm() {
        return "post-form";
    }

    @PostMapping("/board")
    public String createPost(@RequestParam String title, 
                             @RequestParam String content, 
                             Principal principal) {
        String username = principal != null ? principal.getName() : "user";
        postService.createPost(username, title, content);
        return "redirect:/board";
    }
}
