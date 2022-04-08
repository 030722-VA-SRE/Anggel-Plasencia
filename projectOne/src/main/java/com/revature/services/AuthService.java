package com.revature.services;

import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.AuthFailedException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

	private UserRepository ur;
	private String secretKey = "SuperSecretKey";
	private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	public AuthService(UserRepository ur) {
		super();
		this.ur = ur;

	}

	
	public String userReg(User user) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", user.getId());
		claims.put("username", user.getUsername());
		claims.put("roles", user.getRoles().toString());
		
		JwtBuilder token = Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 2000000)).setSubject(user.getUsername())
				.signWith(SignatureAlgorithm.HS256, secretKey);
		
		ur.save(user);
		LOG.info("A new user was created");
		
		return token.compact();
		
	}// end of registration
	
	
	
//	public String login(String username, String password) {
//
//		User user = ur.findByUsername(username);
//
//		if (user == null || !user.getPassword().equals(password)) {
//			throw new AuthFailedException("Login failed, try again!");
//
//		}
//
//		LOG.info("User " + user.getUsername() + "'s credentials validated.");
//
//		return user.getId() + ":" + user.getRoles().toString();
//
//	}// end of login
	
	

	public String userLogin(String username, String password) {
		User user = ur.findByUsername(username);
		
		if(user == null || !user.getPassword().equals(password)) {
			throw new AuthFailedException("Login failed: username or password is incorrect.");
		}
		
		LOG.info("User " + user.getUsername() + "'s credentials validated.");
		
		Map<String,Object> claims = new HashMap<>();
		claims.put("id", user.getId());
		claims.put("username", username);
		claims.put("roles", user.getRoles());
		
		JwtBuilder token = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+2000000))
				.setSubject(username)
				.signWith(SignatureAlgorithm.HS256, secretKey);
		
        return token.compact();
				
	}// end of login
	
	
	
	
	
//	public void verify(String token) {
//		if (token == null) {
//			throw new AuthFailedException("null token");
//		}
//
//		// current tokens format is: [id]:[role], split into {id, role}
//		String[] splitToken = token.split(";");
//
//		User principal = ur.findById(Integer.valueOf(splitToken[0])).orElse(null);
//
//		if (principal == null || !principal.getRoles().toString().equals(splitToken[1])
//				|| !principal.getRoles().toString().equals("ADMIN")
//				|| !principal.getRoles().toString().equals("OWNER")) {
//
//			throw new AuthFailedException("Unable to verify token of value: " + splitToken[0] + ", " + splitToken[1]);
//		}
//
//		LOG.info("Token Verified!");
//		MDC.put("UserId", principal.getId());
//
//	}// end of verify

	public Claims verifyUser(String token) {
		Claims jwt = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
		
		System.out.println(jwt.get("username").toString()+" is correct!");
		return jwt;
	}
	
	
	
	
	
	
	
	
	
}
