package com.websocket.app.dto;

import java.util.Date;

public class UserDTO {

   private Long id;
   private String username;
   private String password;
   private String displayName;
   private String status;
   private Date createDate;
   private Date updateDate;
   
   
public Date getCreateDate() {
	return createDate;
}

public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}

public Date getUpdateDate() {
	return updateDate;
}

public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

   public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getDisplayName() {
      return displayName;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

}
