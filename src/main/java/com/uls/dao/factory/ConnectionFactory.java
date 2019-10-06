package com.uls.dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class creates a connection to the local database. I'm leaning on the
 * singleton pattern here.
 * Created on 2019-09-03
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public class ConnectionFactory {

	private static final String HOST = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE_NAME = "modul_183";
	private static final String TIME_ZONE = "serverTimezone=UTC";
	private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE_NAME + "?" + TIME_ZONE;
	private static final String USERNAME = "stefan";
	private static final String PASSWORD = "sD&XHVg!Rspm";

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);
	private static Connection connection;

	/**
	 * This method creates the connection.
	 * 
	 * @return Either null or a valid connection.
	 */
	public static Connection getConnection() {
		if (connection == null) {
			LOGGER.debug("Creating new database connection!");
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				if (connection != null) {
					LOGGER.debug(
							"Successfully created connection: '{}', with URL: '{}', Username: '{}' and Password: '{}'!",
							connection, URL, USERNAME, PASSWORD.replaceAll(".", "*"));
					LOGGER.info("Successfully created a new connection to database!");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				LOGGER.error("Failed to create new connection, Msg: '{}'!", sqle.getMessage());
			}
		}
		return connection;
	}

}
