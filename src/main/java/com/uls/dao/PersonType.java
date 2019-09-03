package com.uls.dao;

public enum PersonType {

	ID("personId"),
	USERNAME("username"),
	FIRSTNAME("firstname"),
	LASTNAME("lastname"),
	PASSWORD("password"),
	BIRTHDAY("birthday");
	
	private String type;
	
	PersonType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
