package com.revature.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDTO;
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
	public ResponseEntity<List<UserDTO>> getAllUsers(String username) {

		return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user) {

		us.createUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id) {

		return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") int id) {

		return new ResponseEntity<>(us.updateUser(id, user), HttpStatus.OK);
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {

		
		us.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	

//	@PostMapping("/authenticate")
//	public ResponseEntity<String> 
//	

}
