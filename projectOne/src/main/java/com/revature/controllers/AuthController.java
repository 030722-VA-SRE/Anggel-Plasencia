 package com.revature.controllers;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthService as;
	private UserRepository ur;
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	public AuthController(AuthService as, UserRepository ur) {
		super();
		this.as = as;
		this.ur = ur;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestParam(name="username")String username, @RequestParam(name="password")String password){
		MDC.put("requestId", UUID.randomUUID().toString());
		String token = as.userReg(new User(username,password));
		
		// sets authorization header
		HttpHeaders hh = new HttpHeaders();
		hh.set("Authorization", token);
		
		LOG.info(username +" successfully registered.");
		
		return new ResponseEntity<>("Registration successful.",hh,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		
		
		MDC.put("requestId", UUID.randomUUID().toString());
		LOG.debug("Logging in...");
		
		 String token = as.userLogin(username, password);
		
	
		HttpHeaders hh = new HttpHeaders();

		hh.set("Authorization", token);

		LOG.info("User: " + username + "Login Successful!");
		
		return new ResponseEntity<>("Login Successful!", hh, HttpStatus.OK);

	}//end of login
	
	
	
	

}//end of class
