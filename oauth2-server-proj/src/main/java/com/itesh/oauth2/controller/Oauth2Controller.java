package com.itesh.oauth2.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.itesh.oauth2.token.Token;

@RestController
@RequestMapping("/oauth2")
public class Oauth2Controller {

	@Autowired
	private RestTemplate restTemplate; 
	
	@GetMapping(value = "/callback")
	public Token callback(@RequestParam String code) {
		System.out.println("Oauth2Controller - callback()!");
		
		String credentials = "client_id_sample:client_password_sample";
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", "Basic " + encodedCredentials);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("code", code);
		params.add("grant_type", "authorization_code");
		params.add("redirect_uri", "http://localhost:8081/oauth2/callback");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/oauth/token", request, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			Gson gson = new Gson();
			// {"access_token":"ae0eec09-70d4-494e-9521-259fb24abdbf","token_type":"bearer","refresh_token":null,"expires_in":29999,"scope":"read"}
			return gson.fromJson(response.getBody(), Token.class);
		}
		
		return null;
	}
	
	/**
	 * 토큰 갱신 여부를 확인하는 API. 
	 */
	@GetMapping(value = "/token/refresh")
	public Token refreshToken(@RequestParam String refreshToken) {
		System.out.println("Oauth2Controller - refreshToken()!");
		
		String credentials = "client_id_sample:client_password_sample";
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Authorization", "Basic " + encodedCredentials);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("refresh_token", refreshToken);
		params.add("grant_type", "refresh_token");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/oauth/token", request, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			Gson gson = new Gson();
			// {"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjU3MTEzMDksInVzZXJfbmFtZSI6bnVsbCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6ImFmMDVlYWJlLWVmM2QtNDU1NC1iMTMwLWYyNmEwMDA5NGM5MSIsImNsaWVudF9pZCI6ImNsaWVudF9pZF9zYW1wbGUiLCJzY29wZSI6WyJyZWFkIl19.i_xAwNOd5ZvSms6lTX-AB_dlfvqFV2B1rDS2cSfW2VQ","token_type":"bearer","refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOm51bGwsInNjb3BlIjpbInJlYWQiXSwiYXRpIjoiYWYwNWVhYmUtZWYzZC00NTU0LWIxMzAtZjI2YTAwMDk0YzkxIiwiZXhwIjoxNTY1NzI1MjI4LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZDA2OWZjZmEtZWM4Yi00YWQwLWIzMzItNjQyYzkzNjgxOTY4IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkX3NhbXBsZSJ9.0Ou3gNv0mdSW8kVncsvwfq7gdTwkSK_Nol_e3t2RoDc","expires_in":35999,"scope":"read"}
			return gson.fromJson(response.getBody(), Token.class);
		}
		
		return null;
	}
	
}
