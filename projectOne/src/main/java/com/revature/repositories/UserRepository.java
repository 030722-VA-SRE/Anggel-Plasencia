package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.models.UserRoles;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
	public List<User>findUsersByRole(UserRoles role);

	
	
}
