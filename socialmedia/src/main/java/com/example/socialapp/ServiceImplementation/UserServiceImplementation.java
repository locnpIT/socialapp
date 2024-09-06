package com.example.socialapp.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.socialapp.Models.User;
import com.example.socialapp.Repository.UserRepository;
import com.example.socialapp.Service.UserService;

@Service
public class UserServiceImplementation implements UserService {
	
	private final UserRepository userRepository;
	
	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
		
	}

	@Override
	public User registerUser(User user) {
		
		User saveUser = new User();
		saveUser.setId(user.getId());
		saveUser.setFirstName(user.getFirstName());
		saveUser.setLastName(user.getLastName());
		saveUser.setEmail(user.getEmail());
		saveUser.setPassword(user.getPassword());
		
		User savedUser = this.userRepository.save(saveUser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer id) throws Exception{
		Optional<User> user = this.userRepository.findById(id);
		if(user.isPresent())
			return user.get();
		
		throw new Exception("Not found user have id = " + id);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = this.userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception{
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		user1.getFollowing().add(user2.getId());
		
		this.userRepository.save(user1);
		this.userRepository.save(user2);
		
		return user1;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception{
		Optional<User> updateUser = this.userRepository.findById(userId);
		
		if(updateUser.isPresent()) {
			User userAfterUpdate = updateUser.get();
			
			userAfterUpdate.setFirstName(user.getFirstName());
			userAfterUpdate.setLastName(user.getLastName());
			userAfterUpdate.setEmail(user.getEmail());
			return this.userRepository.save(userAfterUpdate);
		}
		
		else {
			throw new Exception("User with id = " + user.getId() + " is not found");
		}
	}

	@Override
	public List<User> searchUser(String query) {
		
		
		return this.userRepository.searchUser(query);
	}
	
	

}
