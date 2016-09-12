package com.websocket.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -584851396856240476L;

	@Id
	private String sessionId;

	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	private String token;
	private String id;
	private String username;
	private String displayName;
	private Boolean isActiveFl = Boolean.FALSE;
	private Date startTime;
	private Date expiryTime;
	private String logoImagePath;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogoImagePath() {
		return logoImagePath;
	}
	public void setLogoImagePath(String logoImagePath) {
		this.logoImagePath = logoImagePath;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getIsActiveFl() {
		return isActiveFl;
	}
	public void setIsActiveFl(Boolean isActiveFl) {
		this.isActiveFl = isActiveFl;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	public int hashCode() {
		String _token = this.token;
		if (null != _token)
			return this.token.hashCode();
		else
			return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Session))
			return false;
		Session userSession = (Session) obj;
		return userSession.token.equals(this.token);
	}
}
