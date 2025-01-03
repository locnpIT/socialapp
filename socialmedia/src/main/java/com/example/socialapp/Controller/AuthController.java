package com.example.socialapp.Controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.Config.JwtProvider;
import com.example.socialapp.Models.User;
import com.example.socialapp.Repository.UserRepository;
import com.example.socialapp.Request.LoginRequest;
import com.example.socialapp.Response.AuthResponse;
import com.example.socialapp.Service.CustomUserDetailsService;
import com.example.socialapp.Service.UserService;

import io.jsonwebtoken.Jwt;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserService userService;
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final CustomUserDetailsService customUserDetailsService;
	
	
	public AuthController(UserService userService, UserRepository userRepository, 
			PasswordEncoder passwordEncoder,
			CustomUserDetailsService customUserDetailsService) {
		this.userService = userService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.customUserDetailsService = customUserDetailsService;
	}
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		
		User isExist = this.userRepository.findByEmail(user.getEmail());
		if(isExist != null) {
			throw new Exception("This email already used with another account!");
		}
		
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		User savedUser = userRepository.save(newUser);
		
		Authentication auuthentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		String token = JwtProvider.generateToken(auuthentication);
		
		AuthResponse authResponse = new AuthResponse(token, "Register success!");
		
		
		return authResponse;
	}
	
	@PostMapping("/signin")
	public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = 
				authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse(token, "Login success!");
		
		
		return authResponse;
	}
	
	private Authentication authenticate(String email, String password) {
	
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Password is wrong!");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
	
}
