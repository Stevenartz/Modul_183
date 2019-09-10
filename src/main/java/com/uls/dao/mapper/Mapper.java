package com.uls.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uls.dao.type.PersonType;
import com.uls.model.Person;

/**
 * 
 * @author sulri
 *
 */
public class Mapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 * @param rs
	 * @return
	 */
	public List<Person> mapResultSetToPersonList(ResultSet rs) {
		List<Person> personList = new ArrayList<>();
		LOGGER.debug("Mapping ResultSet to person list!");
		try {
			if (rs != null) {
				while (rs.next()) {
					Person person = new Person();
					person.setId(rs.getLong(PersonType.ID.toString()));
					person.setUsername(rs.getString(PersonType.USERNAME.toString()));
					person.setFirstname(rs.getString(PersonType.FIRSTNAME.toString()));
					person.setLastname(rs.getString(PersonType.LASTNAME.toString()));
					person.setPassword(rs.getString(PersonType.PASSWORD.toString()));
					GregorianCalendar calendar = new GregorianCalendar();
					calendar.setTime(rs.getDate(PersonType.BIRTHDAY.toString()));
					person.setBirthday(calendar);
					personList.add(person);
				}
			} else {
				LOGGER.debug("Tried to map ResultSet to person list, but ResultSet is null!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("Failed to map ResultSet to person list, Msg: '{}'!", e.getMessage());
		}
		return personList;
	}
}
