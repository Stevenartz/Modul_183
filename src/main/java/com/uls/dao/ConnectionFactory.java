package com.uls.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private final static String HOST = "localhost";
	private final static int PORT = 3306;
	private final static String DATABASE_NAME = "modul_183";
	private final static String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE_NAME;
	private final static String USERNAME = "root";
	private final static String PASSWORD = "";
	
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
