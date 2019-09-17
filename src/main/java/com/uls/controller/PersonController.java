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

import com.uls.dao.personDAO.IPersonDAO;
import com.uls.dao.personDAO.PersonDAO;
import com.uls.model.Person;
import com.uls.security.RequestHandler;

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
	private RequestHandler reqHandler;
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	private PersonController() {
		LOGGER.debug("PersonController initialized!");
		personDAO = new PersonDAO();
		reqHandler = new RequestHandler();
	}

	/**
	 * 
	 * @param headers
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/getPersonByUsername", method = RequestMethod.GET)
	public Person getPersonByUsername(@RequestHeader Map<String, String> headers) {
		LOGGER.debug("--- New Request ---");
		LOGGER.info("User trying to get a Person by his username!");
		Person person = null;
		Object usernameClaim;
		String username;
		Claims claims = reqHandler.checkAuthorization(headers);
		LOGGER.debug("Claims set to: '{}'!", claims);

		if (claims != null) {
			usernameClaim = claims.get("username");
			if (usernameClaim != null) {
				username = usernameClaim.toString().trim();
				person = personDAO.lookupPersonByUsername(username);
				if (person != null) {
					LOGGER.debug("Successfully found person with username: '{}'!", username);
					LOGGER.info("Person found and set!");
				} else {
					LOGGER.debug("No person found with username: '{}'!", username);
					LOGGER.info("No person found!");
				}
			} else {
				LOGGER.debug("No Claim found with id 'username'!");
			}
		} else {
			LOGGER.debug("No Claims found in Token!");
		}
		LOGGER.debug("--- End of Request ---");
		return person;
	}

	/**
	 * 
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public List<Person> getPersons(@RequestHeader Map<String, String> headers) {
		LOGGER.debug("--- New Request ---");
		List<Person> personList = null;
		LOGGER.info("User trying to get a List with all persons!");
		if (reqHandler.checkAuthorization(headers) != null) {
			LOGGER.debug("Select all Persons from database!");
			personList = personDAO.selectAllPersons();
			if (personList != null) {
				LOGGER.info("List with persons successfully filled!");
			} else {
				LOGGER.info("List with persons empty!");
			}
		} else {
			LOGGER.debug("No Claims found in Token!");
			LOGGER.info("Wasn't able to load persons!");
		}
		LOGGER.debug("--- End of Request ---");
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
