package com.websocket.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.websocket.app.model.User;

public interface UserRepository extends MongoRepository<User, String> {

   User findByUsernameAndPassword(String username, String password);

   List<User> findByUsernameNot(String username);

   User findByUsername(String username);
}
