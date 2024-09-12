package com.example.socialapp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.socialapp.Models.User;
import com.example.socialapp.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
		
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found with email " + username);
			
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		
		
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	
	

}
