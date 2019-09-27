package com.uls.dao.personDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uls.dao.factory.ConnectionFactory;
import com.uls.dao.manager.QueryManager;
import com.uls.dao.mapper.Mapper;
import com.uls.model.Person;

/**
 * This class executes all database queries specific to the user.
 * Created on 2019-09-03
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public class PersonDAO implements IPersonDAO {

	private QueryManager queryManager;
	private Mapper mapper;

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * Default constructor.
	 */
	public PersonDAO() {
		queryManager = new QueryManager();
		mapper = new Mapper();
	}

	/**
	 * Looks up exactly one person based on his username.
	 * 
	 * @param username, the username to search for.
	 * @return null or the person found.
	 */
	@Override
	public Person lookupPersonByUsername(String username) {
		Connection conn = ConnectionFactory.getConnection();
		Person person = null;
		try {
			if (username != null) {
				if (conn != null) {
					PreparedStatement pstmt = conn.prepareStatement(queryManager.lookupPersonByUsername());
					if (pstmt != null) {
						pstmt.setString(1, username);
						LOGGER.debug("Setting parameter 1 with value: '{}'!", username);
						List<Person> personList = mapper.mapResultSetToPersonList(pstmt.executeQuery());
						if (personList != null) {
							if (personList.size() == 1) {
								person = personList.get(0);
								LOGGER.info("Successfully found person with username: '{}'!", username);
							} else {
								LOGGER.debug("Expected one Element in personlist but was: '{}'!", personList.size());
							}
						} else {
							LOGGER.debug("Person list is null!");
						}
					} else {
						LOGGER.debug("Couldn't create PreparedStatement!");
					}
				} else {
					LOGGER.warn("Connection not active, connection is null!");
				}
			} else {
				LOGGER.debug("username cannot be null!");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			LOGGER.error("Failed to execute lookupPersonByUsername({}), Msg: '{}'!", username, sqle.getMessage());
		}
		return person;
	}

	/**
	 * Selects all persons in the database.
	 * 
	 * @return null or a list with persons.
	 */
	@Override
	public List<Person> selectAllPersons() {
		Connection conn = ConnectionFactory.getConnection();
		List<Person> personList = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(queryManager.selectAllPersons());
			personList = mapper.mapResultSetToPersonList(pstmt.executeQuery());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return personList;
	}

}