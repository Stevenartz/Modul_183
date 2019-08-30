package com.uls.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hasher {

	private final String ALGORITHM = "MD5";

	public String stringToHash(String text) {
		// Ich habe mich hier bewusst fuer den StringBuilder entschieden, 
		// da die StringBuffer Methoden synchronisiert sind.
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			md.update(text.getBytes());
			for (byte b : md.digest()) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}

}
