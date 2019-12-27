package com.itesh.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.itesh.oauth2.provider.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("4. ecurityConfig - configure(AuthenticationManagerBuilder)!");
		
//		auth.inMemoryAuthentication()
//				.withUser("ite.seokhyeong@gmail.com")
//				.password("{noop}ite.19921123") <- 인코딩 사용 X
//				.roles("USER");
		
		// CustomAuthenticationProvider 사용하도록 설정.
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("5. SecurityConfig - configure(HttpSecurity)!");
		
		http.csrf().disable()
				.headers().frameOptions().disable()
				.and().authorizeRequests().antMatchers("/oauth/**", "/oauth2/callback", "/h2-console/*").permitAll()
				.and().formLogin()
				.and().httpBasic();
	}

}
