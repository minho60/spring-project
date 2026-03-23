package com.example.movie.service;

import com.example.movie.domain.Member;
import com.example.movie.domain.Review;
import com.example.movie.repository.MemberRepository;
import com.example.movie.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public List<ReviewDto> getReviewsByItemId(Long itemId) {
        return reviewRepository.findByItemIdOrderByCreatedAtDesc(itemId).stream()
            .map(review -> {
                Member member = memberRepository.findById(review.getMemberId()).orElse(null);
                String username = member != null ? member.getUsername() : "Unknown";
                String nickname = member != null && member.getNickname() != null ? member.getNickname() : username;
                return new ReviewDto(review.getId(), username, nickname, review.getContent(), review.getRating(), review.getCreatedAt());
            })
            .collect(Collectors.toList());
    }

    public void addReview(Long itemId, String username, String content, Double rating) {
        Optional<Member> memberOpt = memberRepository.findByUsername(username);
        if (memberOpt.isPresent()) {
            Review review = new Review();
            review.setItemId(itemId);
            review.setMemberId(memberOpt.get().getId());
            review.setContent(content);
            review.setRating(rating);
            review.setCreatedAt(LocalDateTime.now());
            reviewRepository.save(review);
        }
    }

    public void deleteReview(Long reviewId, String username) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("Invalid review"));
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        if (!review.getMemberId().equals(member.getId())) {
            throw new IllegalStateException("권한이 없습니다.");
        }
        reviewRepository.deleteById(reviewId);
    }
}
