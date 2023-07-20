package com.example.assignment.userserviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.assignment.model.User;
import com.example.assignment.userrepository.UserRepo;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	UserRepo userrepo;
	
	private User user;
  
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		this.user = userrepo.findByEmail(username);
		if (user != null) {
			return new CustomUserDetail(user);
		}
	 else {
			throw new UsernameNotFoundException("user does not exist");

		}
	  
	   
	}

}

