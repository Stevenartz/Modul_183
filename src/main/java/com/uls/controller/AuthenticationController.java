package com.uls.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
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
import com.uls.security.JWTHelper;
import com.uls.security.SHA512Hasher;

@RestController
public class AuthenticationController {

	private final int USERNAME = 0;
	private final int PASSWORD = 1;

	private IPersonDAO personDAO;
	private SHA512Hasher sha512Hasher;
	private JWTHelper jwtHelper;
	private Authorizer authorizer;

	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private AuthenticationController() {
		LOGGER.debug("AuthenticationController initialized!");
		personDAO = new PersonDAO();
		sha512Hasher = new SHA512Hasher();
		jwtHelper = new JWTHelper();
		authorizer = new Authorizer();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(@RequestHeader Map<String, String> headers, HttpServletResponse response) {
		
		String token;
		if (isUsernameAndPasswordValid(decodeBase64(headers.get("authorization")))) {
			Map<String, Object> claims = new HashMap<>();
			claims.put("username", decodeBase64(headers.get("authorization"))[USERNAME]);
			token = jwtHelper.createJWT(claims, "subjeect", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 60 * 60 * 1000));
		} else {
			try {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
		}
		return token;
	}

	private String[] decodeBase64(String authorization) {
		String base64Credentials = authorization.substring("Basic".length()).trim();
		byte[] decodedBytes = Base64.decodeBase64(base64Credentials);
		return new String(decodedBytes).split(":");
	}

	private boolean isUsernameAndPasswordValid(String[] authCredentials) {
		boolean status = false;
		Person person = personDAO.lookupPersonByUsername(authCredentials[USERNAME]);
		if (person != null) {
			if (person.getPassword().equals(sha512Hasher.stringToHash(authCredentials[PASSWORD]))) {
				status = true;
			}
		}
		return status;
	}

}
