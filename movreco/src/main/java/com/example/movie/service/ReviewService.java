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
                String username = memberRepository.findById(review.getMemberId())
                                                  .map(Member::getUsername)
                                                  .orElse("Unknown User");
                return new ReviewDto(review.getId(), username, review.getContent(), review.getRating(), review.getCreatedAt());
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
}
