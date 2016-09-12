package com.websocket.app.model;

import java.util.Date;

public class Greeting {

   private String content;
   private String receiverName;
   private String senderName;
   private Date pubDate;

   public String getContent() {
      return content;
   }

public String getReceiverName() {
	return receiverName;
}

public void setReceiverName(String receiverName) {
	this.receiverName = receiverName;
}

public String getSenderName() {
	return senderName;
}

public void setSenderName(String senderName) {
	this.senderName = senderName;
}

public void setContent(String content) {
	this.content = content;
}

public Date getPubDate() {
	return pubDate;
}

public void setPubDate(Date pubDate) {
	this.pubDate = pubDate;
}


   
   
}
