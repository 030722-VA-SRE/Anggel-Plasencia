package com.revature.daos;
import java.util.List;

import com.revature.models.Comics;

public interface ComicDao {
	
	//list all
	public List<Comics> getAllComics();
	
	//by criteria
	public Comics getById(int id);
	public List<Comics> getByGenre(String genre);
	public List<Comics> getByComicName(String comic);
	public List<Comics> getByComicAndGenre(String comic, String genre);
	
	//creating, update, and delete 
	public int addComic(Comics comic);
	public boolean updateComic(Comics comic);
	public boolean deleteComic(int id);

	

}
