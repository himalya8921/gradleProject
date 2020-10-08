package com.java.gradleproject;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariConnection {

	private static Connection con = null;
	private static HikariConfig config;
	private static HikariDataSource ds ;



	static
	{
		if(con == null)
		{

			String configFile = "src/main/java/com/java/gradleproject/db.properties";
			
			
//			/GradleProject/src/main/java/com/java/gradleproject/db.properties
			config = new HikariConfig(configFile);

			ds = new HikariDataSource(config);


			try 
			{
				con = ds.getConnection();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws SQLException
	{
		con = ds.getConnection();
		return con;
	}


}
