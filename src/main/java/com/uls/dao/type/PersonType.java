package com.uls.dao.type;

/**
 * This enum provides all the attributes of a person to avoid potential typos.
 * Created on 2019-09-03
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public enum PersonType {

	USERNAME("username"),
	FIRSTNAME("firstname"),
	LASTNAME("lastname"),
	PASSWORD("password"),
	BIRTHDAY("birthday");
	
	private String type;
	
	/**
	 * Default constructor.
	 * 
	 * @param type, the type to set.
	 */
	PersonType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
