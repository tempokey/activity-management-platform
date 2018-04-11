package com.edudigital.cloudy.amp.user.service.util;

import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;

public class SignUtils {

	private final static String SIGN_METHOD = "SHA1";

	private final static String MODE = "UTF-8";

	public static String signSHA1(String plaintext) {
		if (StringUtils.isBlank(plaintext))
			return "";
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance(SIGN_METHOD);
			mdTemp.update(plaintext.getBytes(MODE));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char chars[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				chars[k++] = hexDigits[b >>> 4 & 0xf];
				chars[k++] = hexDigits[b & 0xf];
			}
			return new String(chars);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
}
