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
    private final com.example.movie.service.CommentService commentService;

    @GetMapping("/board")
    public String board(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "board";
    }

    @GetMapping("/board/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model, jakarta.servlet.http.HttpServletResponse response) {
        try {
            model.addAttribute("post", postService.getPostById(id));
            model.addAttribute("comments", commentService.getCommentsByPostId(id));
            return "post-detail";
        } catch (IllegalArgumentException e) {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND);
            return "error/404";
        }
    }

    @GetMapping("/board/new")
    public String newPostForm() {
        return "post-form";
    }

    @PostMapping("/board")
    public String createPost(@RequestParam("title") String title, 
                             @RequestParam("content") String content, 
                             Principal principal) {
        String username = principal != null ? principal.getName() : "user";
        if (title.length() > 100) {
            title = title.substring(0, 100);
        }
        postService.createPost(username, title, content);
        return "redirect:/board";
    }

    @GetMapping("/board/{id}/edit")
    public String editPostForm(@PathVariable("id") Long id, Model model, Principal principal, jakarta.servlet.http.HttpServletResponse response) {
        try {
            com.example.movie.service.PostDto post = postService.getPostById(id);
            if (principal == null || !post.getUsername().equals(principal.getName())) {
                return "redirect:/board/" + id;
            }
            model.addAttribute("post", post);
            return "post-edit";
        } catch (IllegalArgumentException e) {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND);
            return "error/404";
        }
    }

    @PostMapping("/board/{id}/edit")
    public String editPost(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("content") String content, Principal principal) {
        if (principal != null) {
            if (title.length() > 100) {
                title = title.substring(0, 100);
            }
            postService.updatePost(id, principal.getName(), title, content);
        }
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String deletePost(@PathVariable("id") Long id, Principal principal) {
        if (principal != null) {
            postService.deletePost(id, principal.getName());
        }
        return "redirect:/board";
    }

    @GetMapping("/board/{id}/delete")
    public String deletePostGet(jakarta.servlet.http.HttpServletResponse response) {
        response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND);
        return "error/404";
    }
}
