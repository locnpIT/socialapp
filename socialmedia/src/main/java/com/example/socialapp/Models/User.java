package com.example.socialapp.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	
	@Id
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String gender;
	
	private List<Integer> followers = new ArrayList<>();
	
	private List<Integer> following = new ArrayList<>();
	
	
	
	
	public User() {
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public List<Integer> getFollowers() {
		return followers;
	}


	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}


	public List<Integer> getFollowing() {
		return following;
	}


	public void setFollowing(List<Integer> following) {
		this.following = following;
	}

	
	

	
	
	
	
	
	

}
