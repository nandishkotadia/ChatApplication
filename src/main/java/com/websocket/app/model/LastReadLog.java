package com.websocket.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class LastReadLog {

   @Id
   private String id;

   private String senderUsername;

   private String receiverUsername;

   private Date lastReadDate;

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

   public Date getLastReadDate() {
      return lastReadDate;
   }

   public void setLastReadDate(Date lastReadDate) {
      this.lastReadDate = lastReadDate;
   }

}
