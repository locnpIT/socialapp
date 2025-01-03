package com.example.socialapp.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.socialapp.Models.Chat;
import com.example.socialapp.Models.User;
import com.example.socialapp.Repository.ChatRepository;
import com.example.socialapp.Service.ChatService;

@Service
public class ChatServiceImplementation implements ChatService {
	
	
	private ChatRepository chatRepository;
	
	public ChatServiceImplementation(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}
	

	@Override
	public Chat createChat(User reqUser, User user2) {
		
		Chat isExist = chatRepository.findChatByUserId(user2, reqUser);
		
		if(isExist != null) {
			return isExist;
		}
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		
		
		return chatRepository.save(chat); 
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception{
		Optional<Chat> otp = chatRepository.findById(chatId);
		
		if(otp.isEmpty()) {
			throw new Exception("Chat not found with id = " + chatId);
		}
		
		return otp.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) throws Exception {
		
		return this.chatRepository.findByUsersId(userId);
	}

}
