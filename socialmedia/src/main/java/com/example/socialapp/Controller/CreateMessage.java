package com.example.socialapp.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.Models.Message;
import com.example.socialapp.Models.User;
import com.example.socialapp.Service.MessageService;
import com.example.socialapp.Service.UserService;

@RestController
@RequestMapping("/api")
public class CreateMessage {

	private final MessageService messageService;
	
	private final UserService userService;
	
	public CreateMessage(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}
	
	@PostMapping("/messages/chat/{chatId}")
	public Message createMessage(@RequestHeader("Authorization") String jwt,
			@RequestBody Message req,
			@PathVariable(name = "chatId") Integer chatId) throws Exception {
		
		User currentUser = this.userService.findUserByJwt(jwt);
		
		Message message = this.messageService.createMessage(currentUser, chatId, req);
		return message;
	}
	
	@GetMapping("/messages/chat/{chatId}")
	public List<Message> findChatsMessages(@PathVariable Integer chatId) throws Exception{
		
		return this.messageService.findChatsMessages(chatId);
	}
	
	
	
	
	
}
