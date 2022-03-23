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

	public ComicServices(ComicDao testDao) {

		cd = new ComicPostgres();
	}

	public List<Comics> getAllComics() {
		
		return cd.getAllComics();
	}

	// gets the comic id, if the comic's id is not found; throws exception
	public Comics getById(int id) throws ItemNotFoundException {
		Comics comics = cd.getById(id);

		if (comics == null) {
			throw new ItemNotFoundException();
		}
		return comics;

	}

	public boolean addComic(Comics comic) throws ItemNotFoundException {

		 cd.addComic(comic);

		
		return true;
	}

	public boolean updateComic(Comics comic) throws ItemNotFoundException {

		boolean cUpdate = cd.updateComic(comic);

		if (!cUpdate) {

			throw new ItemNotFoundException();
		}
		return cUpdate;
	}

	public boolean deleteComic(int id) throws ItemNotFoundException {

		boolean cDelete = cd.deleteComic(id);

		if (!cDelete) {

			throw new ItemNotFoundException();
		}
		return cDelete;

	}

	// gets the comic genre, if the comic's genre is not found; throws exception
	public List<Comics> getByGenre(String genre) throws ItemNotFoundException {
		List<Comics> gen = cd.getByGenre(genre);

		if (gen == null) {
			throw new ItemNotFoundException();
		}
		return gen;

	}

	public List<Comics> getByComicName(String comic) throws ItemNotFoundException {
		List<Comics> com = cd.getByComicName(comic);

		if (com == null) {
			throw new ItemNotFoundException();
		}
		return com;

	}

	public List<Comics> getByComicAndGenre(String comic, String genre) {

		return cd.getByComicAndGenre(comic, genre);
	}

}// End of CS
