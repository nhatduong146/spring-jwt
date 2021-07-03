package com.jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping({"/profile"})
	public String profile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			System.out.println(authentication.getPrincipal());
			System.out.println("Duong");
		}
		return "profile";
	} 
}
