package com.example.movie.controller;

import com.example.movie.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/{postId}/comment")
    public String addComment(@PathVariable("postId") Long postId, 
                             @RequestParam("content") String content, 
                             Principal principal) {
        if (principal != null) {
            commentService.createComment(postId, principal.getName(), content);
        }
        return "redirect:/board/" + postId;
    }

    @PostMapping("/board/{postId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable("postId") Long postId, 
                                @PathVariable("commentId") Long commentId, 
                                Principal principal) {
        if (principal != null) {
            commentService.deleteComment(commentId, principal.getName());
        }
        return "redirect:/board/" + postId;
    }

    @GetMapping({"/board/{postId}/comment", "/board/{postId}/comment/{commentId}/delete"})
    public String handleGet(jakarta.servlet.http.HttpServletResponse response) {
        response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND);
        return "error/404";
    }
}
