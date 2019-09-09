package com.uls.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * should be splitted into PersonController and Authenticate Controller
 * 
 * @author sulri
 *
 */
@RestController
public class PersonController {

	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private final int TOKEN = 1;
	private IPersonDAO personDAO;

	private PersonController() {
		LOGGER.debug("PersonController initialized!");
		personDAO = new PersonDAO();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/getPersonByUsername", method = RequestMethod.GET)
	public Person getPersonByUsername(@RequestHeader Map<String, String> headers) {

		
		String token[] = headers.get("authorization").split(" ");
		Claims claims = verifyToken(token[TOKEN]);
		Person person = null;
		
		if (claims != null) {
			for (Map.Entry<String, Object> entry : claims.entrySet()) {
				System.out.println(entry.getKey() + " -> " + entry.getValue());
				if (entry.getKey().trim().equals("username")) {
					person = personDAO.lookupPersonByUsername(entry.getValue().toString());
				}
			}
		}
		
		return person;
		
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public List<Person> getPersons(@RequestHeader Map<String, String> headers) {

		String token[] = headers.get("authorization").split(" ");
		System.out.println("Token: " + token[TOKEN]);
		
		Claims claims = verifyToken(token[TOKEN]);
		
		if (claims != null) {
			for (Map.Entry<String, Object> entry : claims.entrySet()) {
				System.out.println(entry.getKey() + " -> " + entry.getValue());
			}
		}
		
		// TODO & method comments aswell
		// hasher.hashPassword();
		// Dummy Data
		return personDAO.selectAllPersons();
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
	
	private Claims verifyToken(String jwt) {
		return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("pasword")).parseClaimsJws(jwt).getBody();
	}

}
