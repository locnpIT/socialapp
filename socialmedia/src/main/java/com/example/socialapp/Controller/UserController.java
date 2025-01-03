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
import org.springframework.web.bind.annotation.RequestHeader;
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
	

	
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(name = "id") Integer id) throws Exception {
		return this.userService.findUserById(id);
	}
	
	@PutMapping("/users")
	public User updateAUser(@RequestHeader("Authorization") String jwt ,@RequestBody User user) throws Exception{
		// sửa từ users/{id} để tránh trường hợp người dùng khác truyền id lên url để sửa
		// không phải của mình => giải pháp dùng jwt
		User reqUser = this.userService.findUserByJwt(jwt);
		
		User updatedUser = userService.updateUser(user, reqUser.getId());
		
		
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
	
	
	@PutMapping("/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable("userId2") Integer userId2) throws Exception {
		
		
		User currentUser = this.userService.findUserByJwt(jwt);
		
		User user = this.userService.followUser(currentUser.getId(), userId2);
		
		
		return user;
	}
	
	
	
	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		return this.userService.searchUser(query);
	}
	
	
	// get user from token 
	@GetMapping("/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
	
//		System.out.println("JWT -------- " + jwt);
		User user = this.userService.findUserByJwt(jwt);
		user.setPassword(null);
		
		return user;
	}
	
}
