package com.example.socialapp.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.Models.Chat;
import com.example.socialapp.Models.User;
import com.example.socialapp.Request.CreateChatRequest;
import com.example.socialapp.Service.ChatService;
import com.example.socialapp.Service.UserService;


@RestController
@RequestMapping("/api")
public class ChatController {
	
	private final ChatService chatService;
	private final UserService userService;
	
	
	public ChatController(ChatService chatService, UserService userService) {
		this.chatService = chatService;
		this.userService = userService;
	}
	
	
	@PostMapping("/chats")
	public Chat createChat(
			@RequestHeader("Authorization") String jwt, 
			@RequestBody CreateChatRequest req) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User user2 = this.userService.findUserById(req.getUserId());
		
		Chat chat = chatService.createChat(reqUser, user2);
		return chat;
	}
	
	@GetMapping("/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = this.userService.findUserByJwt(jwt);
		
		List<Chat> chats = chatService.findUsersChat(user.getId());
		
		return chats;
	}
	
	
	
	
	
}
