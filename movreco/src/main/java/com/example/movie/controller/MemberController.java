package com.example.movie.controller;

import com.example.movie.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @GetMapping("/api/check-username")
    @ResponseBody
    public boolean checkUsername(@RequestParam("username") String username) {
        return memberService.isUsernameTaken(username);
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam("username") String username, 
                               @RequestParam("password") String password, 
                               @RequestParam("email") String email, 
                               @RequestParam("nickname") String nickname,
                               @RequestParam("favoriteGenre") String favoriteGenre,
                               Model model) {
        try {
            memberService.registerUser(username, password, email, nickname, favoriteGenre);
            return "redirect:/login?registered=true";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Username already exists.");
            return "signup";
        }
    }
}
