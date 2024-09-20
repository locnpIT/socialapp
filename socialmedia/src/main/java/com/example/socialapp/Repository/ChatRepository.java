package com.example.socialapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.socialapp.Models.Chat;
import com.example.socialapp.Models.User;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{

	public List<Chat> findByUsersId(Integer userId);
	
	@Query("Select c from Chat c Where :user Member of c.users And :reqUser Member of c.users")
	public Chat findChatByUserId(@Param("user") User user, @Param("reqUser") User reqUser);
	
	
}