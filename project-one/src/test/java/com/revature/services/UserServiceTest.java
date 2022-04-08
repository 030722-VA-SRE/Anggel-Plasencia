package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	static UserRepository ur;
	static UserService us;
	static UserRoles roles;
	static List<User> users = new ArrayList<>();
	static User user1;
	static User user2;

	@BeforeAll
	public static void setup() {
		ur = mock(UserRepository.class);
		us = new UserService(ur);
		user1 = new User(1, "david", "pass1", roles);
	}

	@Test
	void getAllUserTest() {
		when(ur.findAll()).thenReturn(users);
		assertEquals(users, us.getAllUsers());
	}

	@Test
	void getUserByIdTest() {
		when(ur.findById(1)).thenReturn(Optional.of(user1));
		assertEquals(new UserDTO(user1), us.getUserById(1));
	}

	@Test
	void createUserTest() {
		when(ur.findByUsername("david")).thenReturn(user1);
		assertEquals(user1, us.createUser(user1));
	}

	@Test
	void updateUserTest() {
		when(ur.findById(1)).thenReturn(Optional.of(user1));
		when(ur.save(user1)).thenReturn(user1);
		assertEquals(user1, us.updateUser(1, user1));
	}

	@Test
	void deleteUserTest() {
		when(ur.findById(1)).thenReturn(Optional.of(user1));
		assertEquals(true, us.deleteUser(1));
	}

	
}// end of class
