package com.revature.dtos;

import java.util.Objects;

import com.revature.models.User;
import com.revature.models.UserRoles;

public class UserDTO {

	private int id;
	private String username;
	private UserRoles roles;
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public UserDTO(User user) {
		super();
		id = user.getId();
		username = user.getUsername();
		roles = user.getRoles();
		
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	
	public UserRoles getRoles() {
		return roles;
	}

	public void setRoles(UserRoles role) {
		this.roles = role;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, roles, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return id == other.id && roles == other.roles && Objects.equals(username, other.username);
	}


	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", roles=" + roles + "]";
	}



	
}
