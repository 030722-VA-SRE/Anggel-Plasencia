package com.revature.daos;
import java.util.List;

import com.revature.models.Comics;

public interface ComicDao {
	
	
	public List<Comics> getAllComics();
	public Comics getComicById(int id);
	public Comics getComicByGenre(String genre);

}
