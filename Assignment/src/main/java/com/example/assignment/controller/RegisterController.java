package com.example.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.assignment.model.User;
import com.example.assignment.userrepository.UserRepo;
import com.example.assignment.userservice.UserService;


@Controller
public class RegisterController {
@Autowired
 UserRepo userrepo;

@Autowired
UserService userservice;

	@GetMapping("/signup")
	public String getSignupPage() {
	
		return "user_signup";
	}
	
	@PostMapping("/userregister")
	public String userRegistration(@ModelAttribute("user") User user,BCryptPasswordEncoder passwordencoder) {
		boolean result = userservice.saveUser(user,passwordencoder);
		System.out.println("saved sucessfully");
		if(result) {
			return "redirect:/login";
		}
		
		return "user_login";
	}
	 
	@GetMapping("/login")
	public String getLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null|| authentication.getName().equals("anonymousUser")) {
			return "user_login";
		}else {
			return "redirect:/home";
		}
	
	}
	
	//to get logged in user
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			// Assuming you have a UserRepository to retrieve the user by username
			return userrepo.findByEmail(userDetails.getUsername());
		}
		return null; // Or handle the case where the user is not authenticated
	}
}
