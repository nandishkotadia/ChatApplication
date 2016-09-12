package com.websocket.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import com.websocket.app.model.Session;

public class UserAuthenticationService implements AuthenticationUserDetailsService<Authentication> {	
	
	@Override
	public UserDetails loadUserDetails(Authentication token) throws AuthenticationCredentialsNotFoundException {
		UserDetails userDetails = null;
		Session userSession = (Session) token.getCredentials();
		if (userSession != null && userSession.getToken() != null && userSession.getIsActiveFl()) {
			// Setting user Authorities
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			//TODO: call useraccessservice to set access roles in authorities
			userDetails = new User(userSession.getToken(), "", authorities);
		} else {
			StringBuilder sb = new StringBuilder().append("Invalid token: ").append(userSession!= null ? userSession.getToken() : "") ;
			throw new PreAuthenticatedCredentialsNotFoundException(sb.toString());
		}
		return userDetails;
	}
}