package com.itesh.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EncodingTest {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Before
//	public void setUp() throws Exception {
//		passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}

	@Test
	public void encodeTest() {
//		System.out.printf("client_password_sample : %s\n", passwordEncoder.encode("client_password_sample"));
		System.out.printf("ite.19921123 : %s\n", passwordEncoder.encode("ite.19921123"));
	}

}
