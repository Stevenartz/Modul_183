package com.uls.security;

import java.util.Date;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * This class can generate and verify a JWT token.
 * Created on 2019-09-10
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public class JWTHelper {

	public final static String USERNAME = "sub"; //subject, default attribute of JWT token
	private final String JWT_SIGNATURE = "8kw8$Bl3XFhyx5&4gA@!WnKe&5!vNYbc4gorku!L";
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	/**
	 * Default constructor.
	 */
	public JWTHelper() {
		
	}
	
	/**
	 * Generates a JWT token based on the parameters
	 * 
	 * @param claims, the claims to set.
	 * @param subject, the subject to set.
	 * @param issuedAtDate, the issuedAtDate to set.
	 * @param expirationDate, the expirationDate to set.
	 * @return the JWT token.
	 */
	public String createJWT(Map<String, Object> claims, String subject, Date issuedAtDate, Date expirationDate) {
		LOGGER.debug("Creating a JWT!");
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(issuedAtDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, JWT_SIGNATURE)
				.setHeaderParam("typ", "JWT")
				.compact();
	}
	
	/**
	 * Verifies a JWT token.
	 * 
	 * @param jwt, the JWT token.
	 * @return the Claims of the JWT token or null.
	 */
	public Claims verifyToken(String jwt) {
		LOGGER.debug("Verifying JWT!");
		Claims claims = null;
		try {
			claims = Jwts
					.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SIGNATURE))
					.parseClaimsJws(jwt)
					.getBody();
		} catch (SignatureException e) {
			LOGGER.debug("Failed to verify token, Msg: '{}'!", e.getMessage());
			LOGGER.warn("JWT is not valid!");
		}
		if (claims != null) {
			LOGGER.debug("Verification successful!");
		}
		return claims;
	}
	
}
