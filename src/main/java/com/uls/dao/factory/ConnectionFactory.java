package com.uls.dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private final static String HOST = "localhost";
	private final static int PORT = 3306;
	private final static String DATABASE_NAME = "modul_183";
	private final static String TIME_ZONE = "serverTimezone=UTC"; 
	private final static String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE_NAME + "?" + TIME_ZONE;
	private final static String USERNAME = "root";
	private final static String PASSWORD = ""; // Laptop: "" / E0NNDTS: "password"
	
	private static Connection connection;
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return connection;
	}
	
}
