package com.revature.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.models.Comics;
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
	public Comics getById(int id) {
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
	public int addComic(Comics comic) {
		
		String sql = "insert into comics(comic, description, genre, price) " + " values (?,?,?,?)returning id;";
		int generatedId = -1;
		
		
		try(Connection c = ConnectionUtil.getConnectionEnv()){
			
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, comic.getComic());
			ps.setString(2, comic.getDescription());
			ps.setString(3, comic.getGenre());
			ps.setDouble(4, comic.getPrice());
			
			ResultSet rs = ps.executeQuery();
	
			if(rs.next()) {
				generatedId = rs.getInt("id");
				System.out.println(generatedId);
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return generatedId;
	}



	@Override
	public boolean updateComic(Comics comic) {
		String sql = "update comics set comic = ?, description = ?, genre = ?, price = ? where id = ? returning *;";

		try (Connection c = ConnectionUtil.getConnectionEnv()) {

			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, comic.getComic());
			ps.setString(2, comic.getDescription());
			ps.setString(3, comic.getGenre());
			ps.setDouble(4, comic.getPrice());
			ps.setInt(5, comic.getId());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}



	@Override
	public boolean deleteComic(int id) {
		
		String sql = "delete from comics where id = ? returning id;";
		try(Connection c = ConnectionUtil.getConnectionEnv()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
		
	}


	@Override
	public List<Comics> getByGenre(String genre) {
		
		String sql = "select * from comics where genre = ?;";
		List<Comics> comics = new ArrayList<>();
		Comics com = null;
		/*
		 * connection to the db from the ConnectionUtil package  
		 */
		  try (Connection c = ConnectionUtil.getConnectionEnv()){
			  
			// Using the connection established above, we create a prepared statement  
			PreparedStatement ps = c.prepareStatement(sql);

			// setting the sql statement above with the genre that the user is looking for
			ps.setString(1, genre);
			
			// executes the query from the prepared statement and assigns the query result to ResultSet  
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				com = new Comics();
				com.setId(rs.getInt("id"));
				com.setComic(rs.getString("comic"));
				com.setDescription(rs.getString("description"));
				com.setGenre(rs.getString("genre"));
				com.setPrice(rs.getDouble("price"));
				comics.add(com);
			}	
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		// if comic with the given genre is found then return that comic, if not then return null
		return comics;
	}

	
	@Override
	public List<Comics> getByComicName(String comic) {
		String sql = "select * from comics where comic = ?;";
		List<Comics> comics = new ArrayList<>();
		Comics com = null;
		/*
		 * connection to the db from the ConnectionUtil package  
		 */
		  try (Connection c = ConnectionUtil.getConnectionEnv()){
			  							
			// Using the connection established above, we create a prepared statement  
			PreparedStatement ps = c.prepareStatement(sql);

			// setting the sql statement above with the comic that the user is looking for
			ps.setString(1, comic);
			
			// executes the query from the prepared statement and assigns the query result to ResultSet  
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				com = new Comics();
				com.setId(rs.getInt("id"));
				com.setComic(rs.getString("comic"));
				com.setDescription(rs.getString("description"));
				com.setGenre(rs.getString("genre"));
				com.setPrice(rs.getDouble("price"));
				
				comics.add(com);
			}	
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		// if comic with the given comic is found then return that comic, if not then return null
		return comics;
	}
	
	

	@Override
	public List<Comics> getByComicAndGenre(String comic, String genre) {
		String sql = "select * from comics where comic = ? and genre = ?";
		List<Comics> comics = new ArrayList<>();
		Comics com = null;
		/*
		 * connection to the db from the ConnectionUtil package  
		 */
		  try (Connection c = ConnectionUtil.getConnectionEnv()){
			  
			// Using the connection established above, we create a prepared statement  
			PreparedStatement ps = c.prepareStatement(sql);

			// setting the sql statement above with the comic and genre that the user is looking for
			ps.setString(1, comic);
			ps.setString(2, genre);
			
			// executes the query from the prepared statement and assigns the query result to ResultSet  
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				com = new Comics();
				com.setId(rs.getInt("id"));
				com.setComic(rs.getString("comic"));
				com.setDescription(rs.getString("description"));
				com.setGenre(rs.getString("genre"));
				com.setPrice(rs.getDouble("price"));
				comics.add(com);
			}	
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		// if comic with the given comic is found then return that comic, if not then return null
		return comics;
		
	}

}// end of ComicPostgres
