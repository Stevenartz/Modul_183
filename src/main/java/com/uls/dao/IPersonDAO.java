package com.uls.dao;

import java.util.List;

import com.uls.model.Person;

public interface IPersonDAO {

	Person lookupPersonById(int id);
	List<Person> getAllPersons();
	boolean insertPerson();
	boolean updateUser();
	boolean deleteUser();
	
}
