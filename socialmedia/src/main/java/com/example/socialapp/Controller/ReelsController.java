package com.example.socialapp.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.Models.Reels;
import com.example.socialapp.Models.User;
import com.example.socialapp.Service.ReelsService;
import com.example.socialapp.Service.UserService;

@RestController
@RequestMapping("/api")
public class ReelsController {

	private final UserService userService;
	private final ReelsService reelsService;
	
	public ReelsController(UserService userServices, ReelsService reelsService) {
		this.userService = userServices;
		this.reelsService = reelsService;
	}
	
	
	@PostMapping("/reels")
	public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) {
		
		User reqUser = this.userService.findUserByJwt(jwt);
		Reels createdReels = this.reelsService.createReels(reels, reqUser);
		return createdReels;
	}
	
	@GetMapping("/reels")
	public List<Reels> findAllReels(){
		
		List<Reels> reels = this.reelsService.findAllReels();
		return reels;
	}
	
	@GetMapping("/reels/user/{userId}")
	public List<Reels> findUsersReels(@PathVariable(name = "userId") Integer userId){
		
		List<Reels> reelsUser = this.reelsService.findUserReels(userId);
		
		return reelsUser;
	}
	
	
	
	
}
