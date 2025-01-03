package com.example.socialapp.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.socialapp.Models.Chat;
import com.example.socialapp.Models.Message;
import com.example.socialapp.Models.User;
import com.example.socialapp.Repository.ChatRepository;
import com.example.socialapp.Repository.MessageRepository;
import com.example.socialapp.Service.ChatService;
import com.example.socialapp.Service.MessageService;

@Service
public class MessageServiceImplementation implements MessageService{
	
	
	private final MessageRepository messageRepository;
	
	private final ChatService chatService;
	
	private final ChatRepository chatRepository;
	
	public MessageServiceImplementation(MessageRepository messageRepository, 
			ChatService chatService,
			ChatRepository chatRepository) {
		this.messageRepository = messageRepository;
		this.chatService = chatService;
		this.chatRepository = chatRepository;
	}
	

	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {
		Message newMessage = new Message();
		
		
		Chat chat = this.chatService.findChatById(chatId);
		
		newMessage.setChat(chat);
		
		newMessage.setContent(req.getContent());
		
		newMessage.setImage(req.getImage());
		
		newMessage.setUser(user);
		
		newMessage.setTimestamp(LocalDateTime.now());
		
		newMessage = this.messageRepository.save(newMessage);
		
		chat.getMessages().add(newMessage);
		this.chatRepository.save(chat);
		
		return newMessage;
	}

	@Override
	public List<Message> findChatsMessages (Integer chatId) throws Exception {
		
		Chat chat = this.chatService.findChatById(chatId);	
		
		if(chat == null) {
			throw new Exception("Cannot find this chat");
		}
		
		return messageRepository.findByChatId(chatId);
	}
	
	

}
