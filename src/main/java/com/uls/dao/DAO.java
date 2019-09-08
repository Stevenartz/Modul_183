package com.uls.dao;

import com.uls.model.Person;

public class DAO {

	public static void main(String[] args) {
		PersonDAO dao = new PersonDAO();
		System.out.println(">>> dao.selectAllPersons(): ");
		for (Person p : dao.selectAllPersons()) {
			System.out.println(p);
		}
		System.out.println(">>> dao.lookupPersonById(1)");
		System.out.println(dao.lookupPersonById(1));
		System.out.println(">>> dao.lookupPersonByUsername(\"Samu\")");
		System.out.println(dao.lookupPersonByUsername("Samu"));
	}
	
}
