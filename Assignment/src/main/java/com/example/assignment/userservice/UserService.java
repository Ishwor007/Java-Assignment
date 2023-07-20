package com.example.assignment.userservice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.assignment.model.User;

public interface UserService {

	boolean saveUser(User user,BCryptPasswordEncoder passwordencoder);

}
