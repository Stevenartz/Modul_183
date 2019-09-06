package com.uls.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.uls.dao.type.PersonType;
import com.uls.model.Person;

public class Mapper {

	public List<Person> mapResultSetToPersonList(ResultSet rs) {
		List<Person> personList = new ArrayList<>();
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personList;
	}
	
}
