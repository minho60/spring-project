package com.example.movie;

import com.example.movie.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovrecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovrecoApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(MemberService memberService) {
		return args -> {
			memberService.registerDefaultUser();
		};
	}
}
