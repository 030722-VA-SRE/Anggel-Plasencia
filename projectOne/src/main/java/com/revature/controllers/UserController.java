package com.revature.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService us;

	public UserController(UserService us) {
		super();
		this.us = us;
	}

	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(String username){
		
		return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);
	}
	

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user){
		
		us.createUser(user);
		return new ResponseEntity<>("User " + user + " was created!", HttpStatus.CREATED);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<String> getUserById(@RequestBody int id){
			
			return new ResponseEntity<>("User of id" + id + " was found!", HttpStatus.OK);
		}
	
	
//	@PostMapping("/authenticate")
//	public ResponseEntity<String> 
//	
	
}
