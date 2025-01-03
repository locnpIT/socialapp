package com.example.socialapp.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.socialapp.Models.Story;
import com.example.socialapp.Models.User;
import com.example.socialapp.Repository.StoryRepository;
import com.example.socialapp.Service.StoryService;
import com.example.socialapp.Service.UserService;

@Service
public class StoryServiceImplementation implements StoryService{

	private final UserService userService;
	private final StoryRepository storyRepository;
	
	public StoryServiceImplementation(UserService userService, StoryRepository storyRepository) {
		this.userService = userService;
		this.storyRepository = storyRepository;
	}

	@Override
	public Story createStory(Story story, Integer userId) throws Exception {
		Story newStory = new Story();
		User reqUser = this.userService.findUserById(userId);
		
		newStory.setCaption(story.getCaption());
		newStory.setImage(story.getImage());
		newStory.setTimestamp(LocalDateTime.now());
		newStory.setUser(reqUser);
		
		newStory = this.storyRepository.save(newStory);
		
		return newStory;
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) {
		List<Story> listStoryFind = this.storyRepository.findByUserId(userId);
		return listStoryFind;
	}
	
	
	
}
