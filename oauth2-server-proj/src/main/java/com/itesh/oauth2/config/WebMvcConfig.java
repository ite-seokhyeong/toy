package com.itesh.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private static final long MAX_AGE_SECONDS = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("6. WebMvcConfig - addCorsMappings(CorsRegistry)!");
		
		registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("GET", "POST", "PUT", "DELETE")
					.allowedHeaders("*")
					.allowCredentials(true)
					.maxAge(MAX_AGE_SECONDS);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		System.out.println("1. WebMvcConfig - getRestTemplate()!");
		
		return new RestTemplate();
	}
	
	/**
	 * 기본 Encoder는 bcrypt 암호화 사용.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("2. WebMvcConfig - passwordEncoder()!");
		
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder(){
//	return new BCryptPasswordEncoder();
//	}

}
