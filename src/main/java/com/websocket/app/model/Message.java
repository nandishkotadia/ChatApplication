package com.websocket.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Message {

   @Id
   private String id;

   private String senderUsername;

   private String receiverUsername;

   private String content;

   private Date pubDate;


   public String getId() {
      return id;
   }

   public void setId(String id) {
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

   public String getContent() {
      return content;
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
