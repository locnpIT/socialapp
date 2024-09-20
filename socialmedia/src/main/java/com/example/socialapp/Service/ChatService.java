package com.example.socialapp.Service;

import java.util.List;

import com.example.socialapp.Models.Chat;
import com.example.socialapp.Models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId) throws Exception;
	
	

}
