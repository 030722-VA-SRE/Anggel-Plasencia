package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.Comics;
import com.revature.services.ComicServices;

import io.javalin.Javalin;


public class Driver {

	
	public static void main(String []args) {
		
		// Allows Sriver to interact with item service class
		ComicServices cs = new ComicServices();
		
		// Javalin endpoint and code to handle the endpoints
		Javalin app = Javalin.create();
		app.start(8080);
		
		
		// Get all comics from the DB using the comicServices methods to handle http req
		app.get("/comics", (ctx) ->{
				
			ctx.json(cs.getAllComics());
			ctx.status(200);
			
		});
		
		
		
		//Get items by Id
		app.get("comics/{id}", (ctx) -> {
			int id = Integer.parseInt(ctx.pathParam("id"));
			
			try {
			Comics comics = cs.getById(id);
			ctx.json(comics);
			ctx.status(200);	
			}catch (ItemNotFoundException e){
			
				ctx.status(404);
				ctx.result("Comic of id: " + id + " was not found, try again!");
				
				//<-- log file line here -->
			}
			
		});
		
		
		
	}
	 
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
