package com.uls.model;

import java.util.GregorianCalendar;

/**
 * A model class for a person.
 * Created on 2019-08-30
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public class Person {

	private String username, firstname, lastname, password;
	private GregorianCalendar birthday;
	
	/**
	 * Constructor with all attributes.
	 * 
	 * @param id, the id to set.
	 * @param username, the username to set.
	 * @param firstname, the firstname to set.
	 * @param lastname, the lastname to set.
	 * @param password, the password to set.
	 * @param birthday, the birthday to set.
	 */
	public Person(String username, String firstname, String lastname, String password, GregorianCalendar birthday) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.birthday = birthday;
	}

	/**
	 * Default constructor.
	 */
	public Person() {
		
	}	

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the birthday
	 */
	public GregorianCalendar getBirthday() {
		return birthday;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(GregorianCalendar birthday) {
		this.birthday = birthday;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + ", birthday=" + birthday.getTime() + "]";
	}
	
}