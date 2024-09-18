package com.example.socialapp.Service;

import java.util.List;

import com.example.socialapp.Models.Story;

public interface StoryService {
	
	public Story createStory(Story story, Integer userId) throws Exception;
	
	public List<Story> findStoryByUserId(Integer userId);
	
	

}
