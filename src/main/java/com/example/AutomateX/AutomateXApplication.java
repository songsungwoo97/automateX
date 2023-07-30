package com.example.AutomateX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AutomateXApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomateXApplication.class, args);
	}

	@Bean // WebMvcConfigurer 인터페이스를 구현한 객체를 빈으로 등록
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1/**") // 특정 엔드포인트에 대한 CORS 설정
						.allowedOrigins("http://localhost:3000") // 프론트엔드 서버 주소
						.allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
						.allowCredentials(true) // 쿠키 인증 요청 허용
						.maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트 캐싱
			}
		};
	}
}
