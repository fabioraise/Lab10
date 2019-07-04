package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class DBConnect {

	private static String jdbcURL = "jdbc:mysql://localhost/porto2015?serverTimezone=Europe/Rome";
	private static HikariDataSource ds;
	
	public static Connection getConnection() {
		
		if (ds == null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(jdbcURL);
			config.setUsername("root");
			config.setPassword("fabio");
			
			// configurazione MySQL
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			
			ds = new HikariDataSource(config);
		}
		
		try {
			System.out.println("ok");
			return ds.getConnection();
			

		} catch (SQLException e) {
			System.err.println("Errore connessione al DB");
			throw new RuntimeException(e);
		}

	}

}