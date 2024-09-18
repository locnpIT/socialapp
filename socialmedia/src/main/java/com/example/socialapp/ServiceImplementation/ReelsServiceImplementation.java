package com.example.socialapp.ServiceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.socialapp.Models.Reels;
import com.example.socialapp.Models.User;
import com.example.socialapp.Repository.ReelsRepository;
import com.example.socialapp.Service.ReelsService;
import com.example.socialapp.Service.UserService;

@Service
public class ReelsServiceImplementation implements ReelsService {
	
	private final UserService userService;
	private final ReelsRepository reelsRepository;
	
	public ReelsServiceImplementation(UserService userService, ReelsRepository reelsRepository) {
		
		this.userService = userService;
		this.reelsRepository = reelsRepository;
	}
	

	@Override
	public Reels createReels(Reels reels, User user) {
		
		Reels createdReels = new Reels();
		
		createdReels.setTitle(reels.getTitle());
		createdReels.setUser(user);
		createdReels.setVideo(reels.getVideo());
		
		this.reelsRepository.save(createdReels);
		
		return createdReels;
	}

	@Override
	public List<Reels> findAllReels() {		
		return this.reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUserReels(Integer userId) {
		return this.reelsRepository.findByUserId(userId);
	}
	
	

}
