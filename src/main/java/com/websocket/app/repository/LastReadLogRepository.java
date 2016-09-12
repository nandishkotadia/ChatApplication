package com.websocket.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.websocket.app.model.LastReadLog;

public interface LastReadLogRepository extends MongoRepository<LastReadLog, String> {

   LastReadLog findBySenderUsernameAndReceiverUsername(String senderUsername,String receiverUsername);

}
