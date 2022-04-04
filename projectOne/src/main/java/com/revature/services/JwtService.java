package com.revature.services;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
	
	private final String jwt;

	
	
	public JwtService(String jwt) {
		super();
		this.jwt = jwt;
	}


	public String getJwt() {
		return jwt;
	}
	
	
	

}
