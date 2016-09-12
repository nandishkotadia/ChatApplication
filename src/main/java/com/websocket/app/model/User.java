package com.websocket.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class User {

   @Id
   private String id;

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

   public String getId() {
      return id;
   }

   public void setId(String id) {
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

/*@Entity
@Table(name = "stream")
public class Stream {

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   @Column(name = "id")
   private long id;

   @Column(name = "stream_id")
   private String streamId;

   @Column(name = "name")
   private String name;

   @Enumerated(EnumType.STRING)
   @Column(name = "rule_type")
   private RuleType ruleType;

   @Column(name = "channel_def")
   private String channelDef;

   @Enumerated(EnumType.STRING)
   @Column(name = "source_def")
   private Domain sourceDef;

   @Column(name = "rules")
   private String rules;

    @Column(name = "account_id")
   private String accountId;

   @Column(name = "agents_assigned")
   private String agentsAssigned;

   @Column(name = "create_date")
   private Date createDate;

   @Column(name = "update_date")
   private Date updateDate;
 */