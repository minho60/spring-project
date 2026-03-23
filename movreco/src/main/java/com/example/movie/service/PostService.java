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
        if (memberOpt.isPresent()) {
            Post post = new Post();
            post.setMemberId(memberOpt.get().getId());
            post.setTitle(title);
            post.setContent(content);
            post.setViewCount(0);
            post.setCreatedAt(LocalDateTime.now());
            postRepository.save(post);
        }
    }

    private PostDto convertToDto(Post post) {
        String username = memberRepository.findById(post.getMemberId())
                .map(Member::getUsername)
                .orElse("Unknown User");
        return new PostDto(post.getId(), username, post.getTitle(), post.getContent(), post.getViewCount(), post.getCreatedAt());
    }
}
