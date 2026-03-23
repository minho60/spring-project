package com.example.movie.service;

import com.example.movie.domain.Member;
import com.example.movie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerDefaultUser() {
        if (!memberRepository.findByUsername("user").isPresent()) {
            Member member = new Member();
            member.setUsername("user");
            member.setPassword(passwordEncoder.encode("1234"));
            member.setEmail("user@example.com");
            member.setRole("ROLE_USER");
            memberRepository.save(member);
        }
    }

    public void registerUser(String username, String rawPassword, String email) {
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(rawPassword));
        member.setEmail(email);
        member.setRole("ROLE_USER");
        memberRepository.save(member);
    }
}
