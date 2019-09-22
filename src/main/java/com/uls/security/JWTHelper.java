package com.uls.security;

import java.util.Date;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author sulri
 *
 */
public class JWTHelper {

	public final static String USERNAME = "sub"; 
	private final String JWT_SIGNATURE = "8kw8$Bl3XFhyx5&4gA@!WnKe&5!vNYbc4gorku!L";
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 */
	public JWTHelper() {
		
	}
	
	/**
	 * 
	 * @param claims
	 * @param subject
	 * @param issuedAtDate
	 * @param expirationDate
	 * @return
	 */
	public String createJWT(Map<String, Object> claims, String subject, Date issuedAtDate, Date expirationDate) {
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
	 * 
	 * @param jwt
	 * @return
	 */
	public Claims verifyToken(String jwt) {
		return Jwts
				.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SIGNATURE))
				.parseClaimsJws(jwt)
				.getBody();
	}
	
}
