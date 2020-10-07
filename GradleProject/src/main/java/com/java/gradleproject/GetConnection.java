package com.java.gradleproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	
	static Connection con = null;
	
	static Connection getConnection() throws SQLException, ClassNotFoundException
	{
		
	String url = "jdbc:mysql://localhost:3306/aliens";
	String user = "root";
	String password = "MyNewPass";
	
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection con = DriverManager.getConnection(url, user, password);// connection created 

	return con;
	
	}
	

}
