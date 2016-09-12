package com.websocket.app.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websocket.app.dto.SessionDTO;
import com.websocket.app.dto.StatusDTO;
import com.websocket.app.dto.UserDTO;
import com.websocket.app.model.Session;
import com.websocket.app.model.User;
import com.websocket.app.repository.SessionRepository;
import com.websocket.app.repository.UserRepository;
import com.websocket.app.util.Constants;

@Service
public class UserService implements Constants{

   @Autowired
   private UserRepository userRepository;
   
   @Autowired
   private SessionRepository sessionRepository;

   public SessionDTO authenticateUser(UserDTO userDTO) {
      User user = userRepository.findByUsernameAndPassword(userDTO.getUsername(),
            userDTO.getPassword());
      SessionDTO session = new SessionDTO();
      if(user!=null){
	      session.setId(user.getId());
	      session.setUsername(user.getUsername());
	      session.setDisplayName(user.getDisplayName());
      }
      return session;
      /*if (user != null) {
         if (user.getStatus().equalsIgnoreCase("OFFLINE")) {
            user.setStatus("ONLINE");
            userRepository.save(user);
            return "SUCCESS";
         }
         else {
            System.out.println("User is already online");
            return "SUCCESS";
         }

      } else {
         System.out.println("User not found");
         return "USER NOT FOUND";

      }*/
   }

   public List<UserDTO> getFriends(String username){
      /*List<User> friends= userRepository.findByNotUsername(username);*/
      List<User> friends = (List<User>) userRepository.findAll();
      List<UserDTO> friendsDTO = convertIntoUserDTO(friends);

      return friendsDTO;
   }

   private List<UserDTO> convertIntoUserDTO(List<User> friends) {
      List<UserDTO> userDTO =  new ArrayList<UserDTO>();
      for(User f:friends)
      {
         UserDTO user = new UserDTO();
         user.setUsername(f.getUsername());
         user.setDisplayName(f.getDisplayName());
         user.setStatus(f.getStatus());

         userDTO.add(user);
      }
      return userDTO;
   }

   public void updateUserStatus(StatusDTO statusDTO) {
      User user = userRepository.findByUsername(statusDTO.getUsername());
      user.setStatus(statusDTO.getStatus());
      userRepository.save(user);
      //update user status;

   }

   public String registerUser(UserDTO userDTO) {
      User checkUser = userRepository.findByUsername(userDTO.getUsername());
      if(checkUser !=null)
      {
         return "EXISTS";
      }
      User user = new User();
      user.setUsername(userDTO.getUsername());
      user.setDisplayName(userDTO.getDisplayName());
      user.setPassword(userDTO.getPassword());
      user.setStatus("OFFLINE");
      user.setCreateDate(new Date());
      user.setUpdateDate(new Date());
      userRepository.save(user);
      return "SUCCESS";
   }

	public Session fillSession(SessionDTO sessionDTO) {
		Date currentDate = new Date();
		Session session = new Session();
		session.setUsername(sessionDTO.getUsername());
		session.setDisplayName(sessionDTO.getDisplayName());
		session.setIsActiveFl(Boolean.TRUE);
		session.setToken(UUID.randomUUID().toString());
		session.setLogoImagePath(null);
		session.setStartTime(currentDate);
		session.setExpiryTime(new Date(currentDate.getTime() + SESSION_MAX_LIFE));
		session.setId(sessionDTO.getId());
		return session;
	}
	
	public Session saveSession(Session session) {
		return sessionRepository.save(session);
	}

}
