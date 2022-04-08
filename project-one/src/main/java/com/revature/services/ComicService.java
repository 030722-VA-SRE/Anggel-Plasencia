package com.revature.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.controllers.ComicController;
import com.revature.exceptions.ComicNotFoundException;
import com.revature.exceptions.UserExistsException;
import com.revature.models.Comics;
import com.revature.repositories.ComicRepository;
import com.revature.repositories.UserRepository;

@Service
public class ComicService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ComicService.class);
	private ComicRepository cr;
	private UserRepository ur;
	
	@Autowired
	public ComicService(ComicRepository cr, UserRepository ur) {
		super();
		this.cr = cr;
		this.ur = ur;
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

		LOG.debug("Trying to create new Comic");
		cr.getComicByName(comic.getName());
		
		LOG.debug("Comic created!");
		return cr.save(comic);
		
	}
	
	
	@Transactional
	public Comics updateComic(int id, Comics comic){

		Comics c = cr.findById(id).orElseThrow(ComicNotFoundException:: new);
		LOG.debug("Trying to update new Comic");
		c.setId(c.getId());
		
		LOG.debug("Comic updated!");
		return cr.save(comic);
		
	}
	
	
	@Transactional
	public boolean deleteComic(int id) throws ComicNotFoundException{
		LOG.debug("Trying to delete new Comic");
		cr.getById(id);
		
		cr.deleteById(id);
		
		LOG.debug("Comic deleted!");
		return true;

	}
	
	public List<Comics> getComicByName(String name) throws ComicNotFoundException{
		
		return cr.getComicByName(name);
	}
	
	
	public List<Comics> getComicByGenre(String genre) throws ComicNotFoundException{
		
		return cr.getComicByGenre(genre);
	}
	
	
	
	
	public List<Comics> getComicByType(String type) throws ComicNotFoundException{
		return cr.getComicByType(type);
	}
	
	
	
	
	

}
