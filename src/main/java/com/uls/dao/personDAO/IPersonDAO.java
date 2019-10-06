package com.uls.dao.personDAO;

import com.uls.model.Person;
/**
 * This class serves as an interface for the PersonDAO class to restrict the methods.
 * Created on 2019-09-03
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public interface IPersonDAO {

	Person lookupPersonByUsername(String username);
	
}
