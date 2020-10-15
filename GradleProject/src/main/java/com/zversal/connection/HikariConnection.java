package com.zversal.connection;

import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/*
 * HikariCp is a connection pool , which basically is used to get connection  from pool 
 */
//need to make this class as singleton (for this we need to do 3 things)
public class HikariConnection {

	private static HikariConnection hikariobj;
	private HikariConfig config;
	private HikariDataSource ds;

	 public HikariConnection() {
		if (hikariobj == null) {
			String configFile = "resources/db.properties";
			config = new HikariConfig(configFile);
			ds = new HikariDataSource(config);
		}
	}
	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
