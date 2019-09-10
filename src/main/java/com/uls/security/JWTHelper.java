package com.uls.security;

import java.util.Date;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTHelper {

	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public JWTHelper() {
		
	}
	
	public String createJWT(Map<String, Object> claims, String subject, Date issuedAtDate, Date expirationDate) {
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(issuedAtDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, "pasword")
				.setHeaderParam("typ", "JWT")
				.compact();
	}
	
	public Claims verifyToken(String jwt) {
		return Jwts
				.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary("pasword"))
				.parseClaimsJws(jwt)
				.getBody();
	}
	
	
	
}
