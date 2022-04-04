package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {

	private UserRepository ur;
	private JwtService jwt;

	@Autowired
	public AuthService(UserRepository ur, JwtService jwt) {
		super();
		this.ur = ur;
		this.jwt = jwt;
	}

	public UserDTO login(String username, String password) {
		
		User principal = ur.findByUsername(username);
		
		if (principal == null) {
			
		}
		return null;
		
	}

}
