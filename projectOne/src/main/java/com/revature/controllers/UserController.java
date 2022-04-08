package com.revature.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService us;
	private AuthService as;
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	public UserController(UserService us, AuthService as) {
		super();
		this.us = us;
		this.as = as;
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(
			@RequestHeader(value = "Authorization", required = false) String token) {

		// this logic should be handled as a filter
		MDC.put("requestId", UUID.randomUUID().toString());

		// auth logic throws a runtime exception if not verified, better placed as a
		// filter
		Claims claims = as.verifyUser(token);
		if (!claims.get("roles").toString().equals("ADMIN")) {
			LOG.warn("Unauthorized.");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		LOG.info(claims.get("username") + " retrieved the list of users.");
		return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<String> createUser(@RequestHeader(value = "Authorization", required = false) String token,
			@RequestBody User user) {

		Claims claims = as.verifyUser(token);
		if (!claims.get("roles").toString().equals("ADMIN")) {
			LOG.warn("Not Authorized to create a User.");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		us.createUser(user);
		LOG.info(claims.getSubject() + " user: " + user + " was created!");
		return new ResponseEntity<>("User Created!", HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id,
			@RequestHeader(value = "Authorization", required = false) String token) {

		Claims claims = as.verifyUser(token);
		if (!claims.get("roles").toString().equals("ADMIN")) {
			LOG.warn("Unauthorized attempt to create new user.");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		MDC.put("userToken", token);
		UserDTO newDTO = us.getUserById(id);
		MDC.clear();

		return new ResponseEntity<>(newDTO, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id,
			@RequestHeader(value = "Authorization", required = false) String token, @RequestBody User user) {

		Claims claims = as.verifyUser(token);
		if (!claims.get("roles").toString().equals("ADMIN")) {
			LOG.warn("Not Authorized to update the user of id: " + id);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(us.updateUser(id, user), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id,
			@RequestHeader(value = "Authorization", required = false) String token) {

		Claims claims = as.verifyUser(token);
		if (!claims.get("roles").toString().equals("ADMIN")) {
			LOG.warn("Not Authorized to delete the user of id: " + id);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		us.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
