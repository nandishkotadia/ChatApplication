package com.websocket.app.util;

public interface Constants {
	public final static String SESSION_COOKIE = "auth_token";
	public final static String ACCESS_TOKEN = "access_token";
	public final static String SESSION_OBJECT = "MESSAGEAPP_SESSION";
	public static final String PATH_SEPARATOR = "/";
	public final static int COOKIE_MAX_LIFE = 24 * 3600 * 1000;
	public final static String HTTP_HEADER_NAME = "WWW-Authenticate";
	public final static String TOKEN_TYPE = "BASIC";
	public final static int SESSION_MAX_LIFE = 24 * 3600 * 1000;
	public static final String REQUEST_HEADER_TYPE_XFORWARDED = "X-FORWARDED-FOR";
	public static final String MESSAGEAPPADMIN = "LogiNextAdmin";
	public final static String CLIENT_SECRET_KEY = "CLIENT_SECRET_KEY";

}

