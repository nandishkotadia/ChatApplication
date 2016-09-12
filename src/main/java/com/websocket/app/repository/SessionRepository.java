package com.websocket.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.websocket.app.model.Session;

public interface SessionRepository extends MongoRepository<Session, String> {

	Session findByToken(String token);

}
