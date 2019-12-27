package com.itesh.oauth2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itesh.oauth2.entity.Member;
import com.itesh.oauth2.repository.MemberRepository;

@RestController
@RequestMapping(value = "/sh")
public class MemberController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping(value = "/members")
	public List<Member> getMemberList() {
		return memberRepository.findAll();
	}
	/**
	 * 테스트 순서? 
	 * 
	 * @ 인증서버
	 * 		a. 앱 실행 (포트: 8081)
	 * 		b. http://localhost:8081/h2-console 접근. MEMBER / MEMBER_ROLE 정보 INSERT. 
	 * 		c. http://localhost:8081/oauth/authorize?client_id=client_id_sample&redirect_uri=http://localhost:8081/oauth2/callback&response_type=code&scope=read 요청
	 * 				-> b에서 INSERT한 계정 정보로 로그인 -> 허가 -> {access_token} 받아온다. 
	 * 
	 * @ 리소스 서버
	 * 		d. 앱 실행 (포트: 8083)
	 * 		e. http://localhost:8083/h2-console 접근. MEMBER / MEMBER_ROLE 정보 INSERT. 
	 * 		f. (포스트맨 사용) http://localhost:8083/sh/members 요청
	 * 				+ Authorization 선택 -> TYPE: Bearer Token -> Token: {access_token}
	 * 
	 * @ f번 요청 결과? (ex. 전체회원 리스트 조회)
	 [
	    {
	        "id": 1,
	        "uid": "ite.seokhyeong@gmail.com",
	        "upassword": "{bcrypt}$2a$10$sOPt.A5JyAEwyTXaU7A5o.7zEtFu.rA8BQilplr2LZBKpTsOOkdSe",
	        "uname": "seokhyeong",
	        "roles": [
	            "ROLE_USER"
	        ],
	        "username": null,
	        "enabled": true,
	        "password": null,
	        "authorities": [
	            {
	                "authority": "ROLE_USER"
	            }
	        ],
	        "accountNonExpired": true,
	        "accountNonLocked": true,
	        "credentialsNonExpired": true
	    }
	]
	 */
	
}
