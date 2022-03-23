package com.revature.Controllers;

import java.util.List;
import org.eclipse.jetty.http.HttpStatus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.daos.ComicDao;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.Comics;
import com.revature.services.ComicServices;

import io.javalin.http.Context;

public class ComicController {

	private static ComicServices cs = new ComicServices();
	private static Logger log = LogManager.getRootLogger();

	public static void getAllComics(Context ctx) {
		
		String comic = ctx.queryParam("comic");
		String genre = ctx.queryParam("genre");

		// ctx.json(cs.getAllComics());
		
		List<Comics> com;

		if (comic == null && genre == null) {
			ctx.json(cs.getAllComics());

		} else if (comic != null && genre == null) {
			try {
				com = cs.getByComicName(comic);
				ctx.json(com);
				ctx.status(200);

			} catch (ItemNotFoundException e) {

				ctx.status(404);
				ctx.result("Could not find by genre: " + comic);
				e.printStackTrace();
			}

		} else if (comic == null && genre != null) {

			try {

				List<Comics> gen = cs.getByGenre(genre);
				ctx.status(200);
				ctx.result("Here are the genres matching " + gen + ":" );
				ctx.json(gen);
			} catch (ItemNotFoundException e) {
				ctx.status(404);
				ctx.result("Could not find by genre: " + genre);
				e.printStackTrace();
				
			}

		} 
		else {
			
				List<Comics> cg = cs.getByComicAndGenre(comic, genre);
				ctx.json(cg);
				ctx.status(200);

		}

	}// end of getAllComics

	public static void getById(Context ctx) {
		// Retrieves the value of the path param of name id
		String ParamId = ctx.pathParam("id");

		int comicId = Integer.parseInt(ParamId);

		Comics c;

		try {
			c = cs.getById(comicId);

			ctx.json(c);
			ctx.status(200);

		} catch (ItemNotFoundException e) {

			ctx.status(404);
			ctx.result("Unable to find comic of id " + comicId + ".");
			e.printStackTrace();
			log.error("Unable to find comic of id: " + comicId);
			log.error("Exception was thrown: "+ e.fillInStackTrace());
			
		}

	}// end of getbyId

	public static void addComic(Context ctx) throws ItemNotFoundException {

		Comics newComic = ctx.bodyAsClass(Comics.class);

		// ctx.json(newComic);
		
		if (cs.addComic(newComic)) {

			ctx.status(201);
			ctx.result("New comic was created!");
			log.info("User created a new comic: " + newComic);
		} else {

			ctx.status(400);
			ctx.result("Comic was unable to be created.");
			log.error("Comic "+ newComic +" was unable to be created!");
		}
	}// end of add comic

	public static void updateComic(Context ctx) throws ItemNotFoundException {

		int id = Integer.parseInt(ctx.pathParam("id"));
		Comics c = ctx.bodyAsClass(Comics.class);

		c.setId(id);

		if (cs.updateComic(c)) {

			ctx.status(200);
			ctx.result("Successfully updated comic of id: " + id);

		} else {
			ctx.status(400);
			ctx.result("Unable to update comic of id: " + id);
			log.info("Unable to update comic of id: " + id);
		}

	}// end of updateComic

	public static void deleteComic(Context ctx) throws ItemNotFoundException {

		int id = Integer.parseInt(ctx.pathParam("id"));

		if (cs.deleteComic(id)) {

			ctx.status(200);
			ctx.result("Successfully deleted comic of id: " + id);
			log.info("Successfully deleted comic of id: " + id);
		} else {
			ctx.status(400);
			ctx.result("Unable to delete comic of id: " + id);
			log.info("Unable to delete comic of id: " + id);
		}

	}// end of delete Comic

}// end of ComicController
