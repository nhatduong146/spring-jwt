package com.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping({"/", "home"})
	public String home(HttpServletRequest request) {
		System.out.println(request.getHeader("Authorization"));
		return "home";
	} 
	
	@GetMapping({"/login"})
	public String login() {
		return "login";
	}
}
