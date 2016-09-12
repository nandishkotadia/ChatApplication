package com.websocket.app.dto;

public class UnreadDTO {
	
	private String receiverUsername;
	private long noOfNotifications;
	public String getReceiverUsername() {
		return receiverUsername;
	}
	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}
	public long getNoOfNotifications() {
		return noOfNotifications;
	}
	public void setNoOfNotifications(long noOfNotifications) {
		this.noOfNotifications = noOfNotifications;
	}
	
}	
