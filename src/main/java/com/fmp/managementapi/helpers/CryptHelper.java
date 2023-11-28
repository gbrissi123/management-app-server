package com.fmp.managementapi.helpers;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptHelper {
	static private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11, new SecureRandom());
	
	public static String encode(String value) {
		return encoder.encode(value);
	}
	
	public static boolean compare(String value, String hash) {
		return encoder.matches(value, hash);
	}
}
