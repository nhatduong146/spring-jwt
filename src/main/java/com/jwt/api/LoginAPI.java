package com.jwt.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.CustomUserDetails;
import com.jwt.model.LoginRequest;
import com.jwt.model.LoginRespone;
import com.jwt.security.JwtTokenProvider;

@RestController
public class LoginAPI {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@PostMapping("/api/login")
	public LoginRespone authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), 
						loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
	
		return new LoginRespone(token);
	}
	
	@GetMapping("/api/hello")
	public String hello() {
		return "Hello world";
	}
	
	

}
