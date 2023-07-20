package com.example.assignment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	@GetMapping("/home")
	public String getHomePage() {
		
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession(false).invalidate();

		return "redirect:/home";
	}
}
