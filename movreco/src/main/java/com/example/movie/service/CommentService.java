package com.example.movie.service;

import com.example.movie.domain.Comment;
import com.example.movie.domain.Member;
import com.example.movie.repository.CommentRepository;
import com.example.movie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    
    public List<CommentDto> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public void createComment(Long postId, String username, String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be empty.");
        }
        Optional<Member> memberOpt = memberRepository.findByUsername(username);
        memberOpt.ifPresent(member -> {
            Comment comment = new Comment();
            comment.setPostId(postId);
            comment.setMemberId(member.getId());
            comment.setContent(content);
            comment.setCreatedAt(LocalDateTime.now());
            commentRepository.save(comment);
        });
    }
    
    public void deleteComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        if (!comment.getMemberId().equals(member.getId())) {
             throw new IllegalStateException("권한이 없습니다.");
        }
        commentRepository.deleteById(commentId);
    }
    
    private CommentDto convertToDto(Comment comment) {
        Member member = memberRepository.findById(comment.getMemberId()).orElse(null);
        String username = member != null ? member.getUsername() : "Unknown";
        String nickname = member != null && member.getNickname() != null ? member.getNickname() : username;
        return new CommentDto(comment.getId(), comment.getPostId(), username, nickname, comment.getContent(), comment.getCreatedAt());
    }
}
