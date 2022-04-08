package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserExistsException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.repositories.ComicRepository;
import com.revature.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository ur;
	private static Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	public UserService(UserRepository ur) {
		super();
		this.ur = ur;
	}

	public List<UserDTO> getAllUsers() {

		List<User> users = ur.findAll();

		List<UserDTO> uDTO = users.stream().map(UserDTO::new).collect(Collectors.toList());

		return uDTO;

	}
	
//	public List<UserDTO> getUsersByRole(UserRoles roles){
//		List<User> usr = ur.findUsersByRole(roles);
//		
//		List<UserDTO> roleDTO = usr.stream().map((user) -> new UserDTO(user)).collect(Collectors.toList());
//		return roleDTO;
//	}

	public UserDTO getUserById(int id) throws UserNotFoundException {

		User user = ur.findById(id).orElseThrow(UserNotFoundException::new);

		LOG.info(MDC.get("Token"));
		return new UserDTO(user);
	}

	@Transactional
	public User createUser(User user) throws UserNotFoundException {

		User usr = ur.findByUsername(user.getUsername());
		if (usr != null) {
			return ur.save(user);
		}
		throw new UserNotFoundException();
	}

	@Transactional
	public User updateUser(int id, User user) throws UserNotFoundException {

		User usr = ur.findById(id).orElseThrow(UserNotFoundException::new);

		user.setId(usr.getId());

		return ur.save(user);
	}

	@Transactional
	public boolean deleteUser(int id) throws UserNotFoundException {

		ur.getById(id);

		ur.deleteById(id);
		return true;

	}

}// end of class
