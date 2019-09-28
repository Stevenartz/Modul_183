package com.uls.security;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * This class is responsible for the SHA512.
 * Created on 2019-09-03
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
public class SHA512Hasher {

	private final String ALGORITHM = "SHA-512";
	private final String CHARSET = "UTF-8";

	/**
	 * Encrypts a string with the Hash Algorithm 512.
	 * 
	 * @param text, the text to be hashed.
	 * @return null or the hashed text.
	 */
	public String stringToHash(String text) {
		String hash = null;
		try {
		    MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
		    digest.reset();
		    digest.update(text.getBytes(CHARSET));
		    hash = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return hash;
	}

}
