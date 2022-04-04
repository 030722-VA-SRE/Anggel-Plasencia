package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserExistsException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.ComicRepository;
import com.revature.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository ur;
	private ComicRepository cr;

	@Autowired
	public UserService(UserRepository ur, ComicRepository cr) {
		super();
		this.ur = ur;
		this.cr = cr;
	}

	public List<User> getAllUsers() {

		return ur.findAll();
	}

	public UserDTO getUserById(int id) {

		User user = ur.findById(id).orElseThrow(UserNotFoundException::new);

		return new UserDTO(user);
	}

//	public User getUserByRole(UserRoles role) {
//
//		return ur.findUserByRole(role);
//	}

	@Transactional
	public User createUser(User newUser) throws UserExistsException {

		User usr = ur.findByUsername(newUser.getUsername());

		if (usr != null) {

			return ur.save(newUser);
		}

		return null;
	}
	
	@Transactional
	public void deleteUser(int id) throws UserNotFoundException {

		getUserById(id);
		
		ur.deleteById(id);

	}

	
}//end of class
