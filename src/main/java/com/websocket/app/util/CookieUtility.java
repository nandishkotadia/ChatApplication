package com.websocket.app.util;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;

import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCrypt;


public class CookieUtility {
	
	public static Cookie addCookie(String token, boolean isExpired) {
		Cookie cookie = new Cookie(Constants.SESSION_COOKIE, token);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setMaxAge(isExpired ? -Constants.COOKIE_MAX_LIFE : Constants.COOKIE_MAX_LIFE);
		cookie.setPath("/");
		return cookie;
	}

	public static String buildCookieHeaderValue(String token, boolean isExpired) {
		return Constants.SESSION_COOKIE + "=" + token + ";" + HttpHeaders.EXPIRES + "="
				+ getCookieExpiryDate(isExpired) + ";Path=/;HttpOnly;Secured;";
	}

	public static String getTokenHeaderValue(String token) {
		StringBuilder builder = new StringBuilder();
		builder.append(Constants.TOKEN_TYPE + " " + token);
		return builder.toString();
	}
	
	public static HttpHeaders buildHttpHeader(String token, boolean isExpired, String userName){
		HttpHeaders headers = new HttpHeaders();
		if(!isExpired) {
			String clientKeyHeader = userName;
			headers.add(Constants.HTTP_HEADER_NAME, getTokenHeaderValue(token));
			headers.add(Constants.CLIENT_SECRET_KEY,encodeUsingSalt(clientKeyHeader.trim()));
		}
		return headers;
	}
	
	public static String getCookieExpiryDate(boolean isExpired) {
		Date expirydate = new Date();
		if (!isExpired)
			expirydate.setTime(expirydate.getTime() + Constants.COOKIE_MAX_LIFE);
		else
			expirydate.setTime(expirydate.getTime() - Constants.COOKIE_MAX_LIFE);
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		return df.format(expirydate);
	}
	
	public static String encodeUsingSalt(String string){
		String encryptedCode = "";
		try{
			encryptedCode = BCrypt.hashpw(string.toUpperCase(), BCrypt.gensalt(8, SecureRandom.getInstance("SHA1PRNG")));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return encryptedCode;
	}
}