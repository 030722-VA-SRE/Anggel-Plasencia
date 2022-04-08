package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.exceptions.ComicNotFoundException;
import com.revature.exceptions.UserExistsException;
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

	@Transactional
	public Comics createComic(Comics comic){

		return cr.save(comic);
		
	}
	
	
	@Transactional
	public Comics updateUser(int id, Comics comic){

		Comics c = cr.findById(id).orElseThrow(ComicNotFoundException:: new);
		
		c.setId(c.getId());
		
		return cr.save(comic);
	}
	
	
	@Transactional
	public void deleteUser(int id) throws ComicNotFoundException{

		cr.getById(id);
		
		cr.deleteById(id);

	}
	
	public List<Comics> getCardsByName(String name) throws ComicNotFoundException{
		
		return cr.getComicByName(name);
	}
	
	
	public List<Comics> getComicByGenre(String genre) throws ComicNotFoundException{
		
		return cr.getComicByGenre(genre);
	}
	
	
	
	
	public List<Comics> getComicByType(String type) throws ComicNotFoundException{
		return cr.getComicByType(type);
	}
	
	
	
	
	

}
