package com.websocket.app.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.websocket.app.aspect.CurrentSession;
import com.websocket.app.dto.SessionDTO;
import com.websocket.app.dto.StatusDTO;
import com.websocket.app.dto.UserDTO;
import com.websocket.app.model.Session;
import com.websocket.app.util.CookieUtility;

@Controller
public class UserController {

   @Autowired
   private SimpMessagingTemplate simpMessagingTemplate;

   @Autowired
   private UserService userService;

   @RequestMapping(value = "/user/login", method = { RequestMethod.POST })
   public ResponseEntity<SessionDTO> validateLogin(@RequestBody UserDTO userDTO){
	   try{
	      SessionDTO sessionDTO = userService.authenticateUser(userDTO);
	      Session session = userService.fillSession(sessionDTO);
	      userService.saveSession(session);
	      String token = session.getToken();
	      return new ResponseEntity<SessionDTO>(sessionDTO, CookieUtility.buildHttpHeader(token, false, session.getUsername()), HttpStatus.OK);
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return new ResponseEntity<SessionDTO>(new SessionDTO(),HttpStatus.UNAUTHORIZED);
   }

   @ResponseBody
   @RequestMapping(value = "/user/register", method = { RequestMethod.POST })
   public String validateRegister(@RequestBody UserDTO userDTO) throws JsonProcessingException {
      return userService.registerUser(userDTO);
   }

   @RequestMapping(value = "/user/{username}/friends", method = { RequestMethod.GET })
   public ResponseEntity<List<UserDTO>> getFriends(@CurrentSession Session session,@PathVariable String username){
	   try{
	      List<UserDTO> userDTOs = userService.getFriends(session.getUsername());
	      return new ResponseEntity<List<UserDTO>>(userDTOs,HttpStatus.OK);
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return new ResponseEntity<List<UserDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
   }

   //@MessageMapping("/userStatus")
   @ResponseBody
   @RequestMapping(value = "/user/status/{status_value}", method = RequestMethod.GET )
   public void chat(@CurrentSession Session session, @PathVariable("status_value") String status_value) throws Exception {
	   StatusDTO statusDTO = new StatusDTO();
	   statusDTO.setStatus(status_value.toUpperCase());
	   statusDTO.setUsername(session.getUsername());
	   userService.updateUserStatus(statusDTO);
      simpMessagingTemplate.convertAndSend("/topic/userStatus", statusDTO);
   }

   @MessageMapping("/newUserNotifier")
   public void newUserNotifier(UserDTO userDTO) throws Exception {
      simpMessagingTemplate.convertAndSend("/topic/newUserNotifier", userDTO);
   }
}