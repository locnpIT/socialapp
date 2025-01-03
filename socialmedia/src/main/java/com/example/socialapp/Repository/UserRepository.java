package com.example.socialapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.socialapp.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	public User findByEmail(String email);
	
	
	// Custom query 
	@Query("select u from User u where u.firstName LIKE %:query% OR u.lastName like %:query% OR u.email LIKE %:query%")
	public List<User> searchUser(@Param(value = "query") String query); 
	

}
