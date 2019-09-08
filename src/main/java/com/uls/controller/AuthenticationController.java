package com.uls.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uls.dao.IPersonDAO;
import com.uls.dao.PersonDAO;
import com.uls.model.Person;
import com.uls.security.SHA512Hasher;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {

	private final int USERNAME = 0;
	private final int PASSWORD = 1;

	private IPersonDAO personDAO;
	private SHA512Hasher sha512Hasher = new SHA512Hasher();

	private AuthenticationController() {
		personDAO = new PersonDAO();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(@RequestHeader Map<String, String> headers, HttpServletResponse response) throws IOException {

		System.out
				.println(">>> does match?: " + isUsernameAndPasswordValid(decodeBase64(headers.get("authorization"))));

		if (isUsernameAndPasswordValid(decodeBase64(headers.get("authorization")))) {
			String secret = "pasword";
			Map<String, Object> claims = new HashMap<>();
			claims.put("username", decodeBase64(headers.get("authorization"))[USERNAME]);
			return Jwts.builder().setClaims(claims).setSubject("Ich habe Wochenende!")
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
					.signWith(SignatureAlgorithm.HS512, secret).setHeaderParam("typ", "JWT").compact();
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			return "";
		}
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
