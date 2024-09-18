package com.example.socialapp.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.Models.Story;
import com.example.socialapp.Models.User;
import com.example.socialapp.Service.StoryService;
import com.example.socialapp.Service.UserService;

@RestController
@RequestMapping("/api")
public class StoryController {
	
	private final StoryService storyService;
	private final UserService userService;
	
	public StoryController(StoryService storyService, UserService userService) {
		this.storyService = storyService;
		this.userService = userService;
	}
	
	@PostMapping("/story")
	public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User reqUser = this.userService.findUserByJwt(jwt);
		Story createdStory = this.storyService.createStory(story, reqUser.getId());
		return createdStory;
	}
	
	@GetMapping("/story/user/{userId}")
	public List<Story> getStoryOfUser(@PathVariable(name = "userId") Integer userId){
		
		List<Story> listFind = this.storyService.findStoryByUserId(userId);
		
		return listFind;
	}
	
	
}
