package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ComicNotFoundException;
import com.revature.models.Comics;
import com.revature.repositories.ComicRepository;

@Service
public class ComicService {

	private ComicRepository cr;
	
	@Autowired
	public ComicService(ComicRepository cr) {
		super();
		this.cr = cr;
	}

	public List<Comics> getAllComics() throws ComicNotFoundException{

		return cr.findAll();

	}

	
	public Comics getComicById(int id) throws ComicNotFoundException{
		
		cr.findById(id).orElseThrow(ComicNotFoundException::new);

		return cr.getComicById(id);
	}

//	@Transactional
//	public User createUser(User user){
//
//		User usr = ur.findByUsername(user.getUsername());
//		if (usr == null) {
//			
//			throw new UserExistsException();
//			
//		}
//
//		return ur.save(user);
//	}
//	
//	
//	@Transactional
//	public User updateUser(int id, User user){
//
//		User u = ur.findById(id).orElseThrow(UserNotFoundException:: new);
//		
//		user.setId(u.getId());
//		
//		return ur.save(user);
//	}
//	
//	
//	@Transactional
//	public void deleteUser(int id) throws UserNotFoundException{
//
//		ur.getById(id);
//		
//		ur.deleteById(id);
//
//	}

}
