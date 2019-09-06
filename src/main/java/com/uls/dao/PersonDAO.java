package com.uls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.uls.dao.factory.ConnectionFactory;
import com.uls.dao.manager.QueryManager;
import com.uls.dao.mapper.Mapper;
import com.uls.model.Person;

public class PersonDAO implements IPersonDAO {

	private QueryManager queryManager;
	private Mapper mapper;

	// TODO change user, dont use root as default, to many
	// rights!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	public PersonDAO() {
		queryManager = new QueryManager();
		mapper = new Mapper();
	}

	@Override
	public Person lookupPersonById(int id) {
		Connection conn = ConnectionFactory.getConnection();
		Person person = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(queryManager.lookupPersonById());
			pstmt.setInt(1, id);
			List<Person> personList = mapper.mapResultSetToPersonList(pstmt.executeQuery());
			if (personList != null && personList.size() == 1) {
				person = personList.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public List<Person> selectAllPersons() {
		Connection conn = ConnectionFactory.getConnection();
		List<Person> personList = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(queryManager.selectAllPersons());
			personList = mapper.mapResultSetToPersonList(pstmt.executeQuery());
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
