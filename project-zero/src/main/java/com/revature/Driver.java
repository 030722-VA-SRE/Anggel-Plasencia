package com.revature;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import com.revature.Controllers.ComicController;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.Comics;
import com.revature.services.ComicServices;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {

		// Allows Sriver to interact with item service class
		ComicServices cs = new ComicServices();

		// Javalin endpoint and code to handle the endpoints
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();

			config.defaultContentType = "application/json";
		});

		app.start(8080);

		app.routes(() -> {

			path("comics", () -> {

				get(ComicController::getAllComics);
				post(ComicController::addComic);

				path("{id}", () -> {

					get(ComicController::getById);
					put(ComicController::updateComic);
					delete(ComicController::deleteComic);

				});
				
				
				

			});

		});

//		// Get all comics from the DB using the comicServices methods to handle http req
//		app.get("/comics", (ctx) -> {
//
//			String comic = ctx.queryParam("comic");
//			String genre = ctx.queryParam("genre");
//			

//			try {
//				if(comic == null && genre == null) {
//					ctx.json(cs.getAllComics());
//					
//				}else if(comic != null && genre == null) {
//					List<Comics> comics = cs.getByComicName(comic);
//					ctx.json(comics);
//					
//				}else if(comic == null && genre != null) {
//					List<Comics> comics = cs.getByGenre(genre);
//					ctx.json(comics);
//					
//				}else {
//					List<Comics> comics = cs.getByComicAndGenre(comic, genre);
//					ctx.json(comics);
//				}
//				
//			}catch(ItemNotFoundException e) {
//				ctx.status(404);
//				ctx.result("Item was not found!");
//			}

//		});
//		
//		
//
//		// Get items by Id
//		app.get("comics/{id}", (ctx) -> {
//			int id = Integer.parseInt(ctx.pathParam("id"));
//
//			try {
//				Comics comics = cs.getById(id);
//				ctx.json(comics);
//				ctx.status(200);
//
//				// if specified id is not found, throw exception with specified id and tell the
//				// user to try again
//			} catch (ItemNotFoundException e) {
//
//				ctx.status(404);
//				ctx.result("Comic of id: " + id + " was not found, try again!");
//
//				// <-- log file line here -->
//			}
//
//		});

//		// Get items by Genre
//		app.get("comics/{genre}", (ctx) -> {
//			String genre = (ctx.pathParam("genre"));
//
//			try {
//				Comics comics = cs.getByGenre(genre);
//				ctx.json(comics);
//				ctx.status(200);
//				// if specified genre is not found, throw exception with specified genre and tell the
//				// user to try again
//			} catch (ItemNotFoundException e) {
//
//				ctx.status(404);
//				ctx.result("Comic of genre: " + genre + " was not found, try again!");
//
//				// <-- log file line here -->
//			}
//
//		});

	}// end main

//	// setup JDBC connection
//	public static void jdbcSetup() {
//		
//		 String url = System.getenv("DB_URL");
//		 String user = System.getenv("DB_USER");
//		 String pass = System.getenv("DB_PASS");
//		 
//		 try(Connection c = DriverManager.getConnection(url, user, pass)){
//			 System.out.println(c.getMetaData().getDriverName());
//			 
//			 //c.close();
//		 } catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

}
