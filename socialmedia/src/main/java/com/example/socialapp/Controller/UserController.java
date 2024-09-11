package com.example.socialapp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.Models.User;
import com.example.socialapp.Repository.UserRepository;
import com.example.socialapp.Service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
	
	private final UserRepository userRepository;
	private final UserService userService;
	
	public UserController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}
	

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		
		User savedUser = this.userService.registerUser(user);
		
		return savedUser;
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(name = "id") Integer id) throws Exception {
		return this.userService.findUserById(id);
	}
	
	@PutMapping("/users/{id}")
	public User updateAUser(@RequestBody User user, @PathVariable(name = "id") Integer userId) throws Exception{
		
		User updatedUser = userService.updateUser(user, userId);
		
		return updatedUser;
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id) throws Exception {
		
		Optional<User> currentUser = this.userRepository.findById(id);
		
		if(currentUser.isPresent()) {
			this.userRepository.deleteById(currentUser.get().getId());
			return "Remove user have id = " + id + " success!";
			
		}
		else {
			throw new Exception("User have id = " + id + " not found!");
		}	
	}
	
	
	@PutMapping("/users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable("userId1") Integer userId1, @PathVariable("userId2") Integer userId2) throws Exception {
		
		User user = this.userService.followUser(userId1, userId2);
		
		
		return user;
	}
	
	
	
	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		return this.userService.searchUser(query);
	}
	
}
