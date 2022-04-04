package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.repositories.UserRepository;

@Service
public class AuthService {
	
	private UserRepository ur;

	@Autowired
	public AuthService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	
	
	

}
