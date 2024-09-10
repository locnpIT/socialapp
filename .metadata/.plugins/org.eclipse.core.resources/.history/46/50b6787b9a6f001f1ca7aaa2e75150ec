package com.example.socialapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.socialapp.Models.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	
	@Query(value = "select p from Post p where p.user.id=:userId")
	public List<Post> findPostByUserId(Integer userId);

}
