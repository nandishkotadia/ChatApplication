package com.websocket.app.message.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websocket.app.dto.ContentDTO;
import com.websocket.app.model.Greeting;
import com.websocket.app.model.Message;
import com.websocket.app.repository.MessageMongoAccessor;
import com.websocket.app.repository.MessageRepository;

@Service
public class MessageService {

   @Autowired
   private MessageRepository messageRepository;
   
   @Autowired
   private MessageMongoAccessor messageMongoAccessor; 

   public void save(String senderUsername, String receiverUsername,
         ContentDTO content) {
      Message message = new Message();
      message.setSenderUsername(senderUsername);
      message.setReceiverUsername(receiverUsername);
      message.setContent(content.getContent());
      message.setPubDate(new Date());
      messageRepository.save(message);
   }

   public List<Greeting> getChat(String senderUsername, String receiverUsername) {
	  
	  List<Message> chat = messageMongoAccessor.findBySenderNameAndReceiverName(senderUsername,receiverUsername); 
      /*List<Message> chat = messageRepository
            .findBySenderOneAndReceiverOneAndSenderTwoAndReceiverTwo(senderUsername,
                  receiverUsername, senderUsername,
                  receiverUsername, new Sort(Sort.Direction.DESC, "pubDate"));*/

      List<Greeting> chatDTO = new ArrayList<Greeting>();
      for(Message c:chat)
      {
         Greeting g = convertIntoGreeting(c);
         chatDTO.add(g);
      }
      return chatDTO;
   }

   private Greeting convertIntoGreeting(Message c) {
      Greeting g = new Greeting();
      g.setContent(c.getContent());
      g.setSenderName(c.getSenderUsername());
      g.setReceiverName(c.getReceiverUsername());
      g.setPubDate(c.getPubDate());
      return g;
   }

}
