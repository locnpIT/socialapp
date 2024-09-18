package com.example.socialapp.Service;

import java.util.List;

import com.example.socialapp.Models.Reels;
import com.example.socialapp.Models.User;

public interface ReelsService {
	
	public Reels createReels(Reels reels, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReels(Integer userId);
	
	
	
	

}
