package com.example.assignment.userserviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.assignment.model.User;
import com.example.assignment.userrepository.UserRepo;
import com.example.assignment.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
@Autowired
UserRepo userrepo;

BCryptPasswordEncoder passwordencoder;



	@Override
	public boolean saveUser(User user,BCryptPasswordEncoder passwordencoder) {
		String plain_pwd = user.getPassword();
		String encoded_pwd = passwordencoder.encode(plain_pwd);
		user.setPassword(encoded_pwd);
		System.out.println("saved sucessfully");
		userrepo.save(user);
		return true;
	}

}
