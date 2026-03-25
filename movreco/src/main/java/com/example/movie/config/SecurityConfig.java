package com.example.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                // 모두에게 허용되는 경로 (주로 조회성)
                .requestMatchers("/", "/movies", "/books", "/board", "/board/{id}", "/items/**", "/search", "/login", "/signup", "/api/check-username", "/assets/**", "/error").permitAll()
                // 회원만 사용 가능한 기능 (생성, 수정, 삭제, 북마크 등)
                .requestMatchers(HttpMethod.POST, "/board", "/board/*/comment/**", "/items/*/reviews/**", "/items/*/bookmark").authenticated()
                .requestMatchers("/board/new", "/board/*/edit", "/board/*/delete", "/profile/**").authenticated()
                // 그 외 모든 요청은 인증 필요
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> 
                    response.sendRedirect("/login?login_required=true")
                )
            );
        return http.build();
    }
}
