package com.example.assignment.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assignment.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	
	public User findByEmail(String email);

}
