package com.uls.security;

import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uls.dao.personDAO.IPersonDAO;
import com.uls.dao.personDAO.PersonDAO;
import com.uls.model.Person;

import io.jsonwebtoken.Claims;

public class RequestHandler {

	private JWTHelper jwtHelper;
	private IPersonDAO personDAO;
	private SHA512Hasher sha512Hasher;
	
	private final int TOKEN = 1;
	private final int USERNAME = 0;
	private final int PASSWORD = 1;
	
	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public RequestHandler() {
		jwtHelper = new JWTHelper();
		personDAO = new PersonDAO();
		sha512Hasher = new SHA512Hasher();
	}
	
	/**
	 * 
	 * @param headers
	 * @return
	 */
	public Claims checkAuthorization(Map<String, String> headers) {
		String authorization = headers.get("authorization");
		String token[];
		Claims claims = null;

		if (authorization != null) {
			LOGGER.debug("Authorization found: '{}'!", authorization);
			token = authorization.split(" ");
			if (token.length == 2) {
				claims = jwtHelper.verifyToken(token[TOKEN]);
			} else {
				LOGGER.debug("Token length invalid!");
			}
		} else {
			LOGGER.debug("No authorization found in header!");
		}
		return claims;
	}
	
	/**
	 * 
	 * @param headers
	 * @return
	 */
	public String checkLogin(Map<String, String> headers) {
		String username = null;
		
		String authorization = headers.get("authorization");
		String usernameAndPassword[];
		Person person;
		
		
		if (authorization != null) {
			LOGGER.debug("Authorization found: '{}'!", authorization);
			String base64Credentials = authorization.substring("Basic".length()).trim();
			byte[] decodedBytes = Base64.decodeBase64(base64Credentials);
			usernameAndPassword = new String(decodedBytes).split(":");
			if (usernameAndPassword.length == 2) {
				person = personDAO.lookupPersonByUsername(usernameAndPassword[USERNAME]);
				if (person != null) {
					if (person.getPassword().equals(sha512Hasher.stringToHash(usernameAndPassword[PASSWORD]))) {
						LOGGER.debug("User: '{}' successfully authenticated!", usernameAndPassword[USERNAME]);
						username = usernameAndPassword[USERNAME];
					} else {
						LOGGER.debug("User: '{}' tried to login with wrong password!", usernameAndPassword[USERNAME]);
					}
				} else {
					LOGGER.debug("No person found with usrname: '{}'!", usernameAndPassword[USERNAME]);
				}
			} else {
				LOGGER.debug("Authorization header not long enough, expected: '2', but was : '{}'!", usernameAndPassword.length);
			}
		} else {
			LOGGER.debug("No authorization found in header!");
		}
		return username;
	}
	
}
