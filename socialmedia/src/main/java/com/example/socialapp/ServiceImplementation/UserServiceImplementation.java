package com.example.socialapp.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.socialapp.Config.JwtProvider;
import com.example.socialapp.Exception.UserException;
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
	public User findUserById(Integer id) throws UserException{
		Optional<User> user = this.userRepository.findById(id);
		if(user.isPresent())
			return user.get();
		
		throw new UserException("Not found user have id = " + id);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = this.userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws UserException {
	    User reqUser = findUserById(reqUserId);
	    User user2 = findUserById(userId2);

	    if (reqUser == null || user2 == null) {
	        throw new UserException("User not found");
	    }

	    if (reqUserId.equals(userId2)) {
	        throw new UserException("User cannot follow themselves");
	    }

	    boolean isFollowing = user2.getFollowers().contains(reqUserId);

	    if (!isFollowing) {
	        user2.getFollowers().add(reqUserId);
	        reqUser.getFollowing().add(userId2);
	    } else {
	        user2.getFollowers().remove(reqUserId);
	        reqUser.getFollowing().remove(userId2);
	    }

	    userRepository.save(reqUser);
	    userRepository.save(user2);

	    return reqUser;
	}

	@Override
	public User updateUser(User user, Integer userId) throws UserException{
		Optional<User> updateUser = this.userRepository.findById(userId);
		
		if(updateUser.isPresent()) {
			User userAfterUpdate = updateUser.get();
			if(user.getFirstName() != null)
				userAfterUpdate.setFirstName(user.getFirstName());
			if(user.getLastName() != null)
				userAfterUpdate.setLastName(user.getLastName());
			if(user.getEmail() != null)
				userAfterUpdate.setEmail(user.getEmail());
			if(user.getGender() != null)
				userAfterUpdate.setGender(user.getGender());
			return this.userRepository.save(userAfterUpdate);
		}
		
		else {
			throw new UserException("User with id = " + user.getId() + " is not found");
		}
	}

	@Override
	public List<User> searchUser(String query) {
		
		
		return this.userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		
		User user = this.userRepository.findByEmail(email);
		return user;
	}
	
	

}
