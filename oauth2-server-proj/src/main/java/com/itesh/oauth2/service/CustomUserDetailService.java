package com.itesh.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itesh.oauth2.entity.Member;
import com.itesh.oauth2.repository.MemberRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepository;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker(); 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUid(username).orElseThrow(() -> new UsernameNotFoundException("user is not exitsts"));
		detailsChecker.check(member);
		return member;
	}

}
