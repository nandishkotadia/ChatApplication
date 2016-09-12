package com.websocket.app.dto;

import java.util.Date;

public class LastReadLogDTO {
	
	private Long id;
	private String senderUsername;
	private String receiverUsername;
	private Date lastReadDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSenderUsername() {
		return senderUsername;
	}
	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}
	public String getReceiverUsername() {
		return receiverUsername;
	}
	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}
	public Date getLastReadDate() {
		return lastReadDate;
	}
	public void setLastReadDate(Date lastReadDate) {
		this.lastReadDate = lastReadDate;
	}
	
	
}
