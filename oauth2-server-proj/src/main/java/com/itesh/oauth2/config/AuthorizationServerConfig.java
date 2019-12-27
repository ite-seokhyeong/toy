package com.itesh.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.itesh.oauth2.service.CustomUserDetailService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	//private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	
	@Autowired
	private CustomUserDetailService userDetailService; 
	
	@Value("${security.oauth2.jwt.signkey}")
	private String signKey; 

	/**
	 * oauth 관련 클라이언트 정보를 가져온다. 
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		System.out.println("3. AuthorizationServerConfig - configure(ClientDetailsServiceConfigurer)!");
		
		// http://localhost:8081/oauth/authorize?client_id=client_id_sample&redirect_uri=http://localhost:8081/oauth2/callback&response_type=code&scope=read
		
		/**
		 * Authorization Code 인증 방식. 
		  		- Service Provider가 제공하는 인증 화면에 로그인하고, 클라이언트 앱이 요청하는 리소스 접근 요청을 승인하면 지정한 redirec_uri로 code를 넘겨주는데, 해당 code로 access_token을 얻는다. 
		  
		  		1. [클라이언트 -> 인증서버] 인증 요청 (/oauth/authorize)
		 		2. [인증서버 -> 클라이언트] 로그인, 회원 리소스 접근 요청
		 		
		 		3. [클라이언트 -> 인증서버] 로그인 완료, 회원 리소스 접근 승인
		 		4. [인증서버 -> 클라이언트] redirectUri로 인증 코드 전송
		 				- 로그인 = Authenticaion(입증) : SecutityConfig에서 설정한 계정 사용
		  
		  		5. [클라이언트 -> 인증서버] 인증토큰 요청 (/oauth/token)
		 		6. [인증서버 -> 클라이언트] 인증토큰 반환
		 				- 로그인 후 회원의 리소스를 접근할 수 있도록 권한을 부여받는 것 = Authorization(권한부여) + AccessControl
		 		
		 */
//		clients.inMemory()
//					.withClient("client_id_sample")
//					.secret("{noop}client_password_sample")
//					.redirectUris("http://localhost:8081/oauth2/callback")
//					.authorizedGrantTypes("authorization_code")
//					.scopes("read", "write")
//					.accessTokenValiditySeconds(30000);
		
		// scope : 인증 후 토큰으로 접근할 수 있는 리소스의 범위 (리소스 서버, 즉 api 서버에서 해당 scope 정보로 클라이언트에게 제공할 리소스의 범위를 제한할 수 있다)
		// accessTokenValiditySeconds : 발급된 accessToken의 유효시간(초)
		
		/**
		 * ClientDetailServiceConfigurer
				clientDetailsService에 대한 config 설정
				inMemory / jdbc (jdbc Template 방식의 구현체) / withClientDetails 3가지 방식 제공
		 */
		/**
		 * insert into oauth_client_details(client_id, resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove)
values('client_id_sample',null,'{bcrypt}$2a$10$O0hQvhmTujfHvpn4D8CIg.qxco23G24J1S8Lcl7HrAQmCmp9yQjaO','read,write','authorization_code,refresh_token','http://localhost:8081/oauth2/callback','ROLE_USER',36000,50000,null,null);
		 */
		//client_password_sample : {bcrypt}$2a$10$O0hQvhmTujfHvpn4D8CIg.qxco23G24J1S8Lcl7HrAQmCmp9yQjaO
		// 클라이언트 주입 방식을 jdbc 방식 사용. 
		clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
	}
	
	/**
	 * 토큰 정보를 DB를 사용하여 관리한다. 
	 */
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.tokenStore(new JdbcTokenStore(dataSource)).userDetailsService(userDetailService);
//		//endpoints.tokenStore(new JdbcTokenStore(dataSource));
//	}
	
	/**
	 * 토큰 발급 형식 변경. (bearer -> JWT) 
	 * 		- JWT 방식을 사용하면, 토큰을 저장하는 DB 테이블은 사용하지 않는다. 
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub
		super.configure(endpoints);
		// access_token을 refresh_token으로 갱신받을 수 있도록 설정. (refresh 토큰 정상 유무 체크를 위해, 회원 정보 조회 셋)
		endpoints.accessTokenConverter(jwtAccessTokenConverter()).userDetailsService(userDetailService);
		//endpoints.accessTokenConverter(jwtAccessTokenConverter());
	}
	
	/**
	 * JwtAccessTokenConverter Bean 등록. - 비대칭 키 sign 방식 사용! 
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new FileSystemResource("src/main/resources/sh_oauthjwt.jks"), "sh_oauthjwtpw".toCharArray());
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("sh_oauthjwt"));
		
		return jwtAccessTokenConverter;
		
	}
	
	/**
	 * JwtAccessTokenConverter Bean 등록. - 인증서버 & 리소스 서버의 signKey 공유 방식
	 */
//	@Bean
//	public JwtAccessTokenConverter jwtAccessTokenConverter() {
//		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
//		tokenConverter.setSigningKey(signKey);
//		return tokenConverter;
//		//return new JwtAccessTokenConverter();
//	}
	
	/**
	 * 리소스 서버에서 인증 서버로 토큰 검증 요청을 보낼 때 /oauth/check_token 호출. 해당 요청을 받기 위한 설정. 즉, /oauth/check_token 활성화! 
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
	}
	
}
