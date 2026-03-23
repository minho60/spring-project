package com.example.movie.service;

import com.example.movie.domain.Member;
import com.example.movie.domain.Post;
import com.example.movie.repository.MemberRepository;
import com.example.movie.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public List<PostDto> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
        
        // Increase view count
        post.setViewCount(post.getViewCount() + 1);
        postRepository.save(post);

        return convertToDto(post);
    }

    public void createPost(String username, String title, String content) {
        Optional<Member> memberOpt = memberRepository.findByUsername(username);
        memberOpt.ifPresent(member -> {
            Post post = new Post();
            post.setMemberId(member.getId());
            post.setTitle(title);
            post.setContent(content);
            post.setViewCount(0);
            post.setCreatedAt(LocalDateTime.now());
            postRepository.save(post);
        });
    }

    public void updatePost(Long id, String username, String title, String content) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        if (!post.getMemberId().equals(member.getId())) {
            throw new IllegalStateException("권한이 없습니다.");
        }
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);
    }

    public void deletePost(Long id, String username) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        if (!post.getMemberId().equals(member.getId())) {
            throw new IllegalStateException("권한이 없습니다.");
        }
        postRepository.deleteById(id);
    }

    private PostDto convertToDto(Post post) {
        Member member = memberRepository.findById(post.getMemberId()).orElse(null);
        String username = member != null ? member.getUsername() : "Unknown";
        String nickname = member != null && member.getNickname() != null ? member.getNickname() : username;
        return new PostDto(post.getId(), username, nickname, post.getTitle(), post.getContent(), post.getViewCount(), post.getCreatedAt());
    }
}
