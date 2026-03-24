package com.example.movie.controller;

import com.example.movie.domain.Member;
import com.example.movie.domain.Review;
import com.example.movie.repository.ItemRepository;
import com.example.movie.repository.PostRepository;
import com.example.movie.repository.ReviewRepository;
import com.example.movie.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;
    private final ItemRepository itemRepository;

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

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Member member = memberService.getMemberByUsername(principal.getName());
        List<Review> reviews = reviewRepository.findByMemberIdOrderByCreatedAtDesc(member.getId());

        // 리뷰에 연결된 아이템 이름 맵
        Map<Long, String> itemNames = new HashMap<>();
        for (Review review : reviews) {
            if (!itemNames.containsKey(review.getItemId())) {
                itemRepository.findById(review.getItemId())
                    .ifPresent(item -> itemNames.put(item.getId(), item.getTitle()));
            }
        }

        model.addAttribute("member", member);
        model.addAttribute("reviews", reviews);
        model.addAttribute("itemNames", itemNames);
        model.addAttribute("posts", postRepository.findByMemberIdOrderByCreatedAtDesc(member.getId()));
        return "profile";
    }

    @PostMapping("/profile/delete")
    public String deleteAccount(Principal principal, jakarta.servlet.http.HttpServletRequest request) {
        if (principal != null) {
            Member member = memberService.getMemberByUsername(principal.getName());
            // 리뷰, 글 삭제 후 회원 삭제
            reviewRepository.deleteAll(reviewRepository.findByMemberIdOrderByCreatedAtDesc(member.getId()));
            postRepository.deleteAll(postRepository.findByMemberIdOrderByCreatedAtDesc(member.getId()));
            memberService.deleteMember(member.getId());
            // 세션 무효화 (로그아웃 처리)
            request.getSession().invalidate();
        }
        return "redirect:/";
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
