package com.revature.services;

import org.slf4j.LoggerFactory;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.AuthFailedException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {

	private UserRepository ur;
	private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	public AuthService(UserRepository ur) {
		super();
		this.ur = ur;

	}

	public String registration(User user) {
		return null;
	}
	
	
	public String login(String username, String password) {

		User user = ur.findByUsername(username);

		if (user == null || !user.getPassword().equals(password)) {
			throw new AuthFailedException("Login failed, try again!");

		}

		LOG.info("User " + user.getUsername() + "'s credentials validated.");

		return user.getId() + ":" + user.getRoles().toString();

	}// end of login

	public void verify(String token) {
		if (token == null) {
			throw new AuthFailedException("null token");
		}

		// current tokens format is: [id]:[role], split into {id, role}
		String[] splitToken = token.split(";");

		User principal = ur.findById(Integer.valueOf(splitToken[0])).orElse(null);

		if (principal == null || !principal.getRoles().toString().equals(splitToken[1])
				|| !principal.getRoles().toString().equals("ADMIN")
				|| !principal.getRoles().toString().equals("OWNER")) {

			throw new AuthFailedException("Unable to verify token of value: " + splitToken[0] + ", " + splitToken[1]);
		}

		LOG.info("Token Verified!");
		MDC.put("UserId", principal.getId());

	}// end of verify

}
