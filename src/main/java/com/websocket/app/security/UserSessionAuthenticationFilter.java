package com.websocket.app.security;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.websocket.app.aspect.Log;
import com.websocket.app.model.Session;
import com.websocket.app.repository.SessionRepository;
import com.websocket.app.util.Constants;

public class UserSessionAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter implements Constants{

	private static @Log Logger logger;
	
	@Autowired 
	private SessionRepository sessionRepository;
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return "N/A";
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		
		Session userSession = null;
		String authToken = null;
		String secretKey = null;
		try {
			authToken = getAccessToken(request);
			if (!"".equals(authToken)) {
				userSession = getSession(authToken);
				if (null != userSession) {
					secretKey = request.getHeader(CLIENT_SECRET_KEY);
					secretKey = !StringUtils.isBlank(secretKey) ? secretKey : request.getParameter(CLIENT_SECRET_KEY);
					//logger.info("secretKey = "+secretKey+" Session Value="+userSession.getUserName().toUpperCase() + ":" + modelType);
					if (validateSecretKey(secretKey, userSession.getUsername().toUpperCase()) ){
						Date expiryTime = userSession.getExpiryTime();
						Calendar currentTime = Calendar.getInstance();
						if (!expiryTime.before(currentTime.getTime())) {
							request.setAttribute(SESSION_OBJECT, userSession);
							return userSession;
						}
					}
				}
			}
		} catch (Exception ex) {
			String reqUrl = request.getRequestURL().toString();
		    String queryString = request.getQueryString();
		    if (queryString != null) {
		        reqUrl += "?"+queryString;
		    }
		    logger.error("Error occured while authenticating session for request [{}]", reqUrl);
			logger.error("Error occured while authenticating session for token [{}] and client secret key [{}]", authToken, secretKey);
			logger.error("Error occured while authenticating session - [{}]", ex);
		}
		userSession = new Session();
		userSession.setToken(authToken);
		userSession.setIsActiveFl(false);
		return userSession;
	}

	private Session getSession(String token) {
		Session session =null;
		try{
			session =  sessionRepository.findByToken(token);
		}catch(Exception ex){ 
			//In case Redis Server is down
			logger.error("UserSessionAuthenticationFilter::findByTableIdAndKey():: Error occured while fetching session from Redis [{}]", ex);
		}
		return session;
	}

	// check if request is pre-flight
	@SuppressWarnings("unused")
	private boolean isPreflight(HttpServletRequest request) {
		return "OPTIONS".equals(request.getMethod());
	}

	private String getAccessToken(HttpServletRequest request) {
		String authToken = request.getHeader(HTTP_HEADER_NAME);
		if (!StringUtils.isEmpty(authToken)) {
			String[] tokens = authToken.split(" ");
			if (tokens.length == 2 && tokens[0].equals(TOKEN_TYPE)) {
				return tokens[1];
			}
		} else {
			// handle pre-flight requests here
			authToken = request.getParameter(ACCESS_TOKEN);
			return !StringUtils.isEmpty(authToken) ? authToken : "";
		}
		return "";
	}
	
	public static boolean validateSecretKey(String secretKey, String sessionValue){
		if(!StringUtils.isBlank(secretKey) && BCrypt.checkpw(sessionValue, secretKey)){
			return true;
		}
		return false;
	}
}