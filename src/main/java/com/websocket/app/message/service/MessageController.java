package com.websocket.app.message.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.websocket.app.dto.ContentDTO;
import com.websocket.app.model.Greeting;

@RestController
@ComponentScan(basePackages = { "org.websocket.config,org.websocket.repositories" })
public class MessageController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private MessageService messageService;

	@MessageMapping("/message/sender/{senderUsername}/receiver/{receiverUsername}")
	public void message(@DestinationVariable String senderUsername, @DestinationVariable String receiverUsername,
			ContentDTO message) throws Exception {
		List<Greeting> chat = new ArrayList<Greeting>();
		messageService.save(senderUsername, receiverUsername, message);
		Greeting UIMessage = new Greeting();
		UIMessage.setContent(message.getContent());
		UIMessage.setSenderName(senderUsername);
		UIMessage.setReceiverName(receiverUsername);
		UIMessage.setPubDate(new Date());
		chat.add(UIMessage);

		simpMessagingTemplate.convertAndSend("/topic/sendmessage/" + senderUsername, chat);
		simpMessagingTemplate.convertAndSend("/topic/message/" + receiverUsername, chat);
	}

	@MessageMapping("/chat/sender/{senderUsername}/receiver/{receiverUsername}")
	public void chat(@DestinationVariable String senderUsername, @DestinationVariable String receiverUsername)
			throws Exception {
		List<Greeting> chat = messageService.getChat(senderUsername, receiverUsername);
		simpMessagingTemplate.convertAndSend("/topic/chat/" + senderUsername, chat);
	}
}
