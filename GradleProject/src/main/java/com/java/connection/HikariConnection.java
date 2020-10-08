package com.java.connection;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
 * HikariCp is a connection pool , which basically is used to get connection  from pool 
 */
//need to make this class as singleton (for this we need to do 3 things)

public class HikariConnection {
	
	private static HikariConnection hikariobj = new HikariConnection() ;
	private  Connection con = null;
	private  HikariConfig config;
	private  HikariDataSource ds ;
	
	private HikariConnection()
	{
		if(con == null)
		{
			String configFile = "resources/db.properties";
			
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
	
    public static HikariConnection getInstance()
    {
    	return hikariobj;
    }

	public Connection getConnection()
	{
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
