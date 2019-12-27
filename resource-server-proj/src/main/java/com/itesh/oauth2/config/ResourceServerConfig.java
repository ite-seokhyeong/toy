package com.itesh.oauth2.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import io.micrometer.core.instrument.util.IOUtils;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${security.oauth2.jwt.signkey}")
	private String signKey;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/sh/memebers").authenticated();
		//http.authorizeRequests().antMatchers("/sh/memebers").access("#oauth2.hasScope('read')").anyRequest().authenticated();
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	/**
	 * 인증 서버와 signKey를 공유하는 방식.
	 */
//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
//		tokenConverter.setSigningKey(signKey);
//		return tokenConverter;
//	}

	/**
	 * 공개 키 사용하는 방식. 
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		Resource resource = new ClassPathResource("public_key.txt");
		
		String publicKey = null;
		
		try {
			publicKey = IOUtils.toString(resource.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		tokenConverter.setVerifierKey(publicKey);
		
		return tokenConverter;
	}

}
