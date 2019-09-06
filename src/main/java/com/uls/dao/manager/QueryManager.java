package com.uls.dao.manager;

import com.uls.dao.type.PersonType;

public class QueryManager {

	private final String PERSONS_TABLE = "persons";
	
	public String selectAllPersons() {
		return new StringBuilder()
		.append("SELECT ")
		.append(PersonType.ID)
		.append(", ")
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
		.append(";")
		.toString();
	}
	
	public String lookupPersonById() {
		String selectAllPersonsQuery = selectAllPersons();
		
		return new StringBuilder()
				.append(selectAllPersonsQuery.substring(0, selectAllPersonsQuery.length() - 1))
				.append(" WHERE ")
				.append(PersonType.ID)
				.append(" = ?;")
				.toString();
	}
	
}
