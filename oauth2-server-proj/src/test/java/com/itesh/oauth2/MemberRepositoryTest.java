package com.itesh.oauth2;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.itesh.oauth2.entity.Member;
import com.itesh.oauth2.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void insertMember() {
		/**
		 insert into member (uid, upassword, uname) values ('ite.seokhyeong@gmail.com', '{bcrypt}$2a$10$sOPt.A5JyAEwyTXaU7A5o.7zEtFu.rA8BQilplr2LZBKpTsOOkdSe', 'seokhyeong'); 
		 insert into member_roles (member_id, roles) values (1, 'ROLE_USER');
		 */
		Member member = new Member(); 
		
		member.setUid("ite.seokhyeong@gmail.com");
		member.setUpassword(passwordEncoder.encode("ite.19921123"));
		member.setUname("seokhyeong");
		member.setRoles(Collections.singletonList("ROLE_USER"));
		
		System.out.println("save1!");
		memberRepository.save(member);
		System.out.println("save2!");
	}
	
}
