package com.websocket.app.lastread.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.websocket.app.dto.LastReadLogDTO;
import com.websocket.app.dto.UnreadDTO;

@RestController
@ComponentScan(basePackages = "org.websocket.dtos")
public class LastReadController {

	/*@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;*/

	@Autowired
	private LastReadService lastReadService;

	@MessageMapping("/lastreadstatus")
	public void validateLogin(@RequestBody LastReadLogDTO lastReadLogDTO) throws JsonProcessingException {
		lastReadService.updateLastReadLog(lastReadLogDTO);
	}

	@RequestMapping(value = "/users/{username}/getunreadnotifications", method = { RequestMethod.GET })
	public List<UnreadDTO> getUnreadNotifications(@PathVariable String username)
			throws JsonProcessingException {
		List<UnreadDTO> unreadDTOs = lastReadService.getUnreadNotifications(username);
		return unreadDTOs;
	}
}
