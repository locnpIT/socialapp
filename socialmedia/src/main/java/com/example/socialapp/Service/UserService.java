package com.example.socialapp.Service;

import java.util.List;

import com.example.socialapp.Exception.UserException;
import com.example.socialapp.Models.User;

public interface UserService {

	
	public User registerUser(User user);
	
	public User findUserById(Integer id) throws UserException;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer user1, Integer user2) throws UserException;
	
	public User updateUser(User user, Integer userId) throws UserException;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);

	
	
	
}
