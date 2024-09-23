package com.example.socialapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.socialapp.Models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	public List<Message> findByChatId(Integer chatId);
	
	
}
