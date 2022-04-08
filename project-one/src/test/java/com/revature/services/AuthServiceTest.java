package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dtos.UserDTO;
import com.revature.models.Comics;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.repositories.ComicRepository;
import com.revature.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
	

	static UserRepository ur;
	static AuthService as;
	static User principal;
	static UserDTO uDTO;
	static UserRoles roles;
	
	
	@BeforeAll
	public static void setup() {
		ur = mock(UserRepository.class);
		as = new AuthService(ur);
		principal = new User(1, "angel", "pass4", UserRoles.ADMIN);
		uDTO = new UserDTO(principal);
	}
	
	
	@Test
	void UserLoginTest() {
		when(ur.findByUsername(anyString())).thenReturn(principal);
		assertEquals(principal, as.userLogin("angel", "pass4"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}//end of class
