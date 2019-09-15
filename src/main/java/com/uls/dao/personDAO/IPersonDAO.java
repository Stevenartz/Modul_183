package com.uls.dao.personDAO;

import java.util.List;

import com.uls.model.Person;

public interface IPersonDAO {

	Person lookupPersonByUsername(String username);
	List<Person> selectAllPersons();
	
}
