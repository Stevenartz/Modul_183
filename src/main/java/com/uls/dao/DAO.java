package com.uls.dao;

import com.uls.dao.personDAO.PersonDAO;
import com.uls.dao.songDAO.SongDAO;
import com.uls.model.Person;
import com.uls.model.Song;

public class DAO {

	public static void main(String[] args) {
		PersonDAO personDAO = new PersonDAO();
		SongDAO songDAO = new SongDAO();
		
		System.out.println(">>> personDAO.selectAllPersons(): ");
		for (Person p : personDAO.selectAllPersons()) {
			System.out.println(p);
		}
		System.out.println(">>> personDAO.lookupPersonByUsername(\"Samu\")");
		System.out.println(personDAO.lookupPersonByUsername("Samu"));
		
		System.out.println(">>> songDAO.lookupAllSongsByUsername(1)");
		for (Song song : songDAO.lookupAllSongsByUsername("Samu")) {
			System.out.println(song);
		}

		// TODO Logging the queries
//		System.out.println(new QueryManager().lookupAllSongsById());
	}
	
}
