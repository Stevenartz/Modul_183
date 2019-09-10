package com.uls.security;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;

public class Authorizer {

	private JWTHelper jwtHelper;
	
	private final int TOKEN = 1;
	
	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public Authorizer() {
		jwtHelper = new JWTHelper();
	}
	
	/**
	 * 
	 * @param headers
	 * @return
	 */
	public Claims checkAuth(Map<String, String> headers) {
		String authorization = headers.get("authorization");
		String token[];
		Claims claims = null;

		if (authorization != null) {
			LOGGER.debug("Token found: '{}'", authorization);
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
	
}
