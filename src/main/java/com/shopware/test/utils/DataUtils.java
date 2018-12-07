package com.shopware.test.utils;

import java.util.Random;

public class DataUtils {
	private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static String randomString(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(ALPHA_NUMERIC_STRING.charAt(random.nextInt(ALPHA_NUMERIC_STRING.length())));
		}
		return sb.toString();
	}
	
	public static String randomEmail(String username, String domain) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer(username+"-");
		for (int i = 0; i < 10; i++) {
			sb.append(ALPHA_NUMERIC_STRING.charAt(random.nextInt(ALPHA_NUMERIC_STRING.length())));
		}
		sb.append("@"+domain);
		return sb.toString();
	}
	
	public static String capitalize(String str) {
	    return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

}
