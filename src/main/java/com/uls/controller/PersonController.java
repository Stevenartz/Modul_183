package com.uls.controller;

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
import com.uls.security.JWTHelper;
import com.uls.security.RequestHandler;

import io.jsonwebtoken.Claims;

/**
 * All person requests are handled via this class.
 * Created on 2019-08-30
 * 
 * @author Stefan Ulrich
 * @version 1.0
 *
 */
@RestController
public class PersonController {

	private IPersonDAO personDAO;
	private RequestHandler reqHandler;
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	/**
	 * Default constructor.
	 */
	private PersonController() {
		LOGGER.debug("PersonController initialized!");
		personDAO = new PersonDAO();
		reqHandler = new RequestHandler();
	}

	/**
	 * If the user wants to have a person by his username, this method is called. 
	 * 
	 * @param headers, the headers from the request.
	 * @return Either null or the person found.
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
			usernameClaim = claims.get(JWTHelper.USERNAME);
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

}
