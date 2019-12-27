package com.itesh.oauth2.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.itesh.oauth2.entity.Member;
import com.itesh.oauth2.repository.MemberRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	/** AuthenticationProvider 인터페이스
		화면에서 입력한 로그인 정보와 DB에 가져온 사용자의 정보를 비교해주는 인터페이스
			- authenticate() : 화면에서 사용자가 입력한 로그인 정보를 담고 있는 Authentication 객체를 가진다. */
	
	@Autowired
	private MemberRepository memberRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder; 

	CustomAuthenticationProvider() {
		
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 로그인 화면에서 사용자가 입력한 정보.
		String inputUid = authentication.getName();
		String inputPassword = authentication.getCredentials().toString();
		System.out.println("authenticate() - uid : " + inputUid + ", password : " + inputPassword);
		
		// 입력된 uid에 해당하는 DB 데이터 조회. 
		Member member = memberRepository.findByUid(inputUid).orElseThrow(() -> new UsernameNotFoundException("user is not exitsts"));
		System.out.println("authenticate() - " + member.getUpassword() + ", " + member.getUid() + ", " + member.getRoles());
		
		// password 일치 여부 체크. 
		if (!passwordEncoder.matches(inputPassword, member.getUpassword())) 
			throw new BadCredentialsException("password is not valid");
		
		/** UsernamePasswordAuthenticationToken
			스프링 시큐리티에서는 아이디와 패스워드로 인증할 때 Authentication 인터페이스를 상속받은 UsernamePasswordAuthenticationToken 클래스를 기본으로해서 인증을 받고 있다. 
			Authentication : 인증 정보를 의미하는 인터페이스 */
		// 로그인 정보가 유효한 경우, 회원 정보와 권한 정보로 인증 정보를 생성하여 리턴. 
		return new UsernamePasswordAuthenticationToken(inputUid, inputPassword, member.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
