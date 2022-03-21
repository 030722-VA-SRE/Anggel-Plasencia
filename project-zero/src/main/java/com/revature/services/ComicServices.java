package com.revature.services;
import java.util.List;

import com.revature.daos.ComicDao;
import com.revature.daos.ComicPostgres;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.Comics;


public class ComicServices {

	private ComicDao cd;
	
	public ComicServices() {
		
		cd = new ComicPostgres();
		
	}
	
	
	public List<Comics> getAllComics() {
		
		return cd.getAllComics();
		
	}
	
	
	//gets the comic id, if the comic's id is not found; throws exception
	public Comics getById(int id) throws ItemNotFoundException {
		Comics comics = cd.getComicById(id);
		
		if(comics == null) {
			throw new ItemNotFoundException();
		}
		return comics;
		
	}
	
	//gets the comic genre, if the comic's genre is not found; throws exception
		public Comics getByGenre(String genre) throws ItemNotFoundException {
			Comics comics = cd.getComicByGenre(genre);
			
			if(comics == null) {
				throw new ItemNotFoundException();
			}
			return comics;
			
		}
	
	
	
}
