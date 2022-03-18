package com.revature.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	/*
	 *  Retrieving the JDBC connection to the DB, 
	 *  allows us to reuse the connection rather than creating more instances
	 */
	 private static Connection con; 
	 
	 // returns a connection if it exists or is open, if not close the connection.
	 public static Connection getConnectionEnv() throws SQLException {
		 
		 
		 //Acquiring the credentials for the DB via the system ENV being: url, username, and password
		 String url = System.getenv("DB_URL");
		 String username = System.getenv("DB_USER");
		 String password = System.getenv("DB_PASS");
		 
		 // if statement for resource handling
		 if (con == null || con.isClosed()) {
			 con = DriverManager.getConnection(url, username, password);
		 }
		 return con;
	 }
	

}
