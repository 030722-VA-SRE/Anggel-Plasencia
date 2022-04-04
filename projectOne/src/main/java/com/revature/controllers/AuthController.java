package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserService us;

	@Autowired
	public AuthController(UserService us) {
		super();
		this.us = us;
	}
	
	@PostMapping
	public ResponseEntity<User> login(@RequestParam("username") String username,@RequestParam("password") String password){
		return null;
		
	}
	

}
