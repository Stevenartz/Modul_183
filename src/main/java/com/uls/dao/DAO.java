package com.uls.dao;

public class DAO {

	public static void main(String[] args) {
		PersonDAO dao = new PersonDAO();
		System.out.println(dao.getAllPersons().get(0));
	}
	
}
