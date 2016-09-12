package com.websocket.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.websocket.app.model.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

   //@Query("SELECT m FROM Message m WHERE (sender_username=(:senderUsername) AND receiver_username=(:receiverUsername)) OR  (sender_username=(:receiverUsername) AND receiver_username=(:senderUsername))ORDER BY pub_date ASC")
   //List<Message> findBySenderUsernameAndReceiverUsername(@Param(value = "senderUsername")String senderUsername,@Param(value = "receiverUsername")String receiverUsername);

   //@Query("SELECT count(m) FROM Message m WHERE ((sender_username=(:senderUsername) AND receiver_username=(:receiverUsername)) OR  (sender_username=(:receiverUsername) AND receiver_username=(:senderUsername))) AND pub_date > (:lastReadDate)")
   //long countBySenderUsernameAndReceiverNameAndPubDateGreaterThan(@Param(value = "senderUsername")String senderUsername,@Param(value = "receiverUsername")String receiverUsername,@Param(value = "lastReadDate")Date lastReadDate);

   //@Query("SELECT count(m) FROM Message m WHERE (sender_username=(:senderUsername) AND receiver_username=(:receiverUsername)) OR  (sender_username=(:receiverUsername) AND receiver_username=(:senderUsername))")
   //long countBySenderUsernameAndReceiverName(@Param(value = "senderUsername")String senderUsername,@Param(value = "receiverUsername")String receiverUsername);

   @Query("{'$or' : [{'senderUsername' : ?0 }, {'senderUsername' : ?1 }] , '$or' : [{'receiverUsername' : ?2 }, {'receiverUsername' : ?3 }] }")
   List<Message> findBySenderOneAndReceiverOneAndSenderTwoAndReceiverTwo(String senderUsername1,
         String receiverUsername1, String senderUsername2, String receiverUsername2, Sort sort);
   //add this where this method is used new Sort(Sort.Direction.DESC, "pubDate")

   @Query(value = "{'$or' : [{'senderUsername' : ?0 }, {'senderUsername' : ?1 }] , '$or' : [{'receiverUsername' : ?2 }, {'receiverUsername' : ?3 }] }", count = true)
   long countBySenderOneAndReceiverOneAndSenderTwoAndReceiverTwo(String sender1, String receiver1,
         String senderUsername2, String receiverUsername2);

   @Query(value = "{'$or' : [{'senderUsername' : ?0 }, {'senderUsername' : ?1 }] , '$or' : [{'receiverUsername' : ?2 }, {'receiverUsername' : ?3 }],'pubDate':{ $gt : ?4} }", count = true)
   long countBySenderOneAndReceiverOneAndSenderTwoAndReceiverTwoAndPubDateGreaterThan(String senderUsername1,String receiverUsername1, String senderUsername2, String receiverUsername2,Date lastReadDate, Sort sort);
   //add this where this method is used new Sort(Sort.Direction.DESC, "pubDate")

}
