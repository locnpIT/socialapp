package com.example.socialapp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@@Table(name = "chats")
public class Chat {
	
	private Integer id;
	
	private String chat_name;
	
	private String chat_image;
	
	

}
