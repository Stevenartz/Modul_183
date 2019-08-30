package com.uls.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uls.model.Person;
import com.uls.security.MD5Hasher;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * should be splitted into PersonController and Authenticate Controller
 * @author sulri
 *
 */
@RestController
public class PersonController {

	private final int USERNAME = 0;
	private final int PASSWORD = 1;
	
	private List<Person> personList = new ArrayList<>();
	private MD5Hasher mdHasher = new MD5Hasher();

	private PersonController() {
		personList.add(new Person(1, "stevenartz", "Stefan", "Ulrich", mdHasher.stringToHash("password"),
				createDate(2000, 7, 13)));
		personList
				.add(new Person(2, "samu", "Samuel", "Strehler", mdHasher.stringToHash("pw"), createDate(1999, 9, 22)));
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/persons")
	public List<Person> getPersons() {

		// TODO & method comments aswell
		// hasher.hashPassword();
		// Dummy Data
		return personList;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public String getTest(@RequestHeader Map<String, String> headers, HttpServletResponse response) throws IOException {

		System.out.println(">>> does match?: " + doesUsernameAndPasswordValid(decodeBase64(headers.get("authorization"))));
		
		if (doesUsernameAndPasswordValid(decodeBase64(headers.get("authorization")))) {
			String secret = "pasword";
			Map<String, Object> claims = new HashMap<>();
			return Jwts.builder().setClaims(claims).setSubject("subi").setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 1 * 1000))
					.signWith(SignatureAlgorithm.HS512, secret).compact();
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			return "";
		}
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

	private String[] decodeBase64(String authorization) {
		String base64Credentials = authorization.substring("Basic".length()).trim();
		byte[] decodedBytes = Base64.decodeBase64(base64Credentials);
		return new String(decodedBytes).split(":");
	}

	private boolean doesUsernameAndPasswordValid(String[] authCredentials) {
		boolean status = false;
		for (Person person : personList) {
			if (person.getUsername().equals(authCredentials[USERNAME])
					&& person.getPassword().equals(mdHasher.stringToHash(authCredentials[PASSWORD]))) {
				status = true;
				break;
			}
		}
		return status;
	}

}
