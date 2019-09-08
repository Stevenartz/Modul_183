package com.uls.dao;

import java.util.List;

import com.uls.model.Person;

public interface IPersonDAO {

	Person lookupPersonById(int id);
	Person lookupPersonByUsername(String username);
	List<Person> selectAllPersons();
	boolean insertPerson();
	boolean updateUser();
	boolean deleteUser();
	
}
