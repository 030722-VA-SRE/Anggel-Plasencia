package com.revature.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Comics;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;


public class ComicPostgres implements ComicDao{


	@Override
	public List<Comics> getAllComics() {
		String sql = "select * from comics;";
		List<Comics> comics = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnectionEnv()){
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Comics comicsAll = new Comics();
				comicsAll.setId(rs.getInt("id"));
				comicsAll.setComic(rs.getString("comic"));
				comicsAll.setDescription(rs.getString("description"));
				comicsAll.setGenre(rs.getString("genre"));
				comicsAll.setPrice(rs.getDouble("price"));
				
				comics.add(comicsAll);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return comics;
	}
	
	
	

	@Override
	public Comics getComicById(int id) {
		String sql = "select * from comics where id = ?;";
		Comics comics = null;
		
		/*
		 * Putting the connection in ref variable c to retrieve a 
		 * connection to the db from the ConnectionUtil package  
		 */
		  try (Connection c = ConnectionUtil.getConnectionEnv()){
			  
			// Using the connection established above, we create a prepared statement  
			PreparedStatement ps = c.prepareStatement(sql);
			
			// setting the sql statement above with the id that the user is looking for
			ps.setInt(1, id);
			
			// executes the query from the prepared statement and assigns the query result to ResultSet  
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				comics = new Comics();
				comics.setId(rs.getInt("id"));
				comics.setComic(rs.getString("comic"));
				comics.setDescription(rs.getString("description"));
				comics.setGenre(rs.getString("genre"));
				comics.setPrice(rs.getDouble("price"));
			}	
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		// if comic with the given id is found then return that comic, if not then return null
		return comics;
		
	}




	@Override
	public Comics getComicByGenre(String genre) {
		
		String sql = "select * from comics where to_tsvector(genre) @@ to_tsquery('');";
		Comics comics = null;
		/*
		 * connection to the db from the ConnectionUtil package  
		 */
		  try (Connection c = ConnectionUtil.getConnectionEnv()){
			  
			// Using the connection established above, we create a prepared statement  
			//PreparedStatement ps = c.prepareStatement(sql);
			  Statement s = c.createStatement();
			// setting the sql statement above with the genre that the user is looking for
			//ps.setString(1, genre);
			
			// executes the query from the prepared statement and assigns the query result to ResultSet  
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				comics = new Comics();
				comics.setId(rs.getInt("id"));
				comics.setComic(rs.getString("comic"));
				comics.setDescription(rs.getString("description"));
				comics.setGenre(rs.getString("genre"));
				comics.setPrice(rs.getDouble("price"));
			}	
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		// if comic with the given genre is found then return that comic, if not then return null
		return comics;
	}

}
