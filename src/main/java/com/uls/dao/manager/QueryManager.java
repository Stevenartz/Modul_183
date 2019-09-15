package com.uls.dao.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uls.dao.type.PersonType;
import com.uls.dao.type.SongType;

/**
 * 
 * @author sulri
 *
 */
public class QueryManager {

	private final String PERSONS_TABLE = "persons";
	private final String SONGS_TABLE = "songs";
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 * @return
	 */
	public String selectAllPersons() {
		StringBuilder query = new StringBuilder()
			.append("SELECT ")
			.append(PersonType.USERNAME)
			.append(", ")
			.append(PersonType.FIRSTNAME)
			.append(", ")
			.append(PersonType.LASTNAME)
			.append(", ")
			.append(PersonType.PASSWORD)
			.append(", ")
			.append(PersonType.BIRTHDAY.toString())
			.append(" FROM ")
			.append(PERSONS_TABLE)
			.append(";");
		
		LOGGER.debug("Successfully created the selectAllPersons query: '{}'", query.toString());
		return query.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public String lookupPersonByUsername() {
		String selectAllPersonsQuery = selectAllPersons();
		StringBuilder query = new StringBuilder()
			.append(selectAllPersonsQuery.substring(0, selectAllPersonsQuery.length() - 1))
			.append(" WHERE ")
			.append(PersonType.USERNAME)
			.append(" = ?;");
		
		LOGGER.debug("Successfully created the lookupPersonByUsername query: '{}'", query.toString());
		return query.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public String lookupAllSongsById() {
		StringBuilder query = new StringBuilder()
			.append("SELECT ")
			.append(SongType.ID)
			.append(", ")
			.append(SongType.GENRE)
			.append(", ")
			.append(SongType.TITLE)
			.append(", ")
			.append(SongType.ARTIST)
			.append(", ")
			.append(SongType.LENGTH)
			.append(" FROM ")
			.append(SONGS_TABLE)
			.append(" WHERE ")
			.append(SongType.PERSON_FOREGIN_KEY)
			.append(" = ?");
		
		LOGGER.debug("Successfully created the lookupAllSongsById query: '{}'", query.toString());
		return query.toString();
	}
	
}
