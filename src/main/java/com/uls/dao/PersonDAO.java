package com.uls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.uls.model.Person;

public class PersonDAO implements IPersonDAO {

	private final String TABLE = "persons";
	
	// TODO change user, dont use root as default, to many rights!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@Override
	public Person lookupPersonById(int id) {
		Connection conn = ConnectionFactory.getConnection();
		return null;
	}

	@Override
	public List<Person> getAllPersons() {
		Connection conn = ConnectionFactory.getConnection();
		List<Person> personList = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM " + TABLE);
			ResultSet rs = pstmt.executeQuery();
			personList = new ArrayList<>();
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

	@Override
	public boolean insertPerson() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser() {
		// TODO Auto-generated method stub
		return false;
	}

}
