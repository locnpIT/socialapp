package com.example.socialapp.Service;

import java.util.List;

import com.example.socialapp.Models.Chat;
import com.example.socialapp.Models.Message;
import com.example.socialapp.Models.User;

public interface MessageService {

	
	
	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
	
	
}
