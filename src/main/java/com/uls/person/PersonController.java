package com.uls.person;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import security.Hasher;

/**
 * 
 * @author sulri
 *
 */
@RestController
public class PersonController {

	/**
	 * 
	 * @return
	 */
    @RequestMapping("/persons")
    public List<Person> getPersons() {
    	List<Person> personsList = new ArrayList<>();
    	Hasher hasher = new Hasher();
    	
    	// TODO & method comments aswell
//    	hasher.hashPassword();
    	// Dummy Data
    	personsList.add(new Person(1, "stevenartz", "Stefan", "Ulrich", "password", createDate(2000, 7, 13)));
    	personsList.add(new Person(2, "samu", "Samuel", "Strehler", "pw", createDate(1999, 9, 22)));
    	return personsList;
    }
    
    /**
     * 
     * @param year
     * @param month
     * @param dayOfMonth
     * @return
     */
    private GregorianCalendar createDate(int year, int month, int dayOfMonth) {
    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.set(Calendar.YEAR, year);
    	calendar.set(Calendar.MONTH, month - 1);
    	calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    	return calendar;
    }
	
}
