package com.uls.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uls.dao.IPersonDAO;
import com.uls.dao.PersonDAO;
import com.uls.model.Person;
import com.uls.security.Authorizer;

import io.jsonwebtoken.Claims;

/**
 * should be splitted into PersonController and Authenticate Controller
 * 
 * @author sulri
 *
 */
@RestController
public class PersonController {

	private IPersonDAO personDAO;
	private Authorizer authorizer;

	final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private PersonController() {
		LOGGER.debug("PersonController initialized!");
		personDAO = new PersonDAO();
		authorizer = new Authorizer();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/getPersonByUsername", method = RequestMethod.GET)
	public Person getPersonByUsername(@RequestHeader Map<String, String> headers) {
		Person person = null;
		Claims claims;
		Object usernameClaim;
		String username;

		claims = authorizer.checkAuth(headers);
		if (claims != null) {
			usernameClaim = claims.get("username");
			if (usernameClaim != null) {
				username = usernameClaim.toString().trim();
				person = personDAO.lookupPersonByUsername(username);
				if (person != null) {
					LOGGER.debug("Successfully found person with username: '{}'!", username);
				} else {
					LOGGER.debug("No person found with usrname: '{}'!", username);
				}
			} else {
				LOGGER.debug("No Claim found with id 'username'!");
			}
		} else {
			LOGGER.debug("No Claims found in Token!");
		}

		return person;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public List<Person> getPersons(@RequestHeader Map<String, String> headers) {
		List<Person> personList = null;
		if (authorizer.checkAuth(headers) != null) {
			LOGGER.debug("Select all Persons from database!");
			personList = personDAO.selectAllPersons();
		} else {
			LOGGER.debug("No Claims found in Token!");
		}
		return personList;
	}

//	/**
//	 * 
//	 * @param year
//	 * @param month
//	 * @param dayOfMonth
//	 * @return
//	 */
//	private GregorianCalendar createDate(int year, int month, int dayOfMonth) {
//		GregorianCalendar calendar = new GregorianCalendar();
//		calendar.set(Calendar.YEAR, year);
//		calendar.set(Calendar.MONTH, month - 1);
//		calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//		return calendar;
//	}

}
