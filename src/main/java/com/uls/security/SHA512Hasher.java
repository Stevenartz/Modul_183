package com.uls.security;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA512Hasher {

	private final String ALGORITHM = "SHA-512";
	private final String CHARSET = "UTF-8";

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
