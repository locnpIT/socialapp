package com.example.socialapp.Repository;

import java.util.List;

import org.hibernate.type.TrueFalseConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.socialapp.Models.Post;

import jakarta.transaction.Transactional;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	
	@Query(value = "select p from Post p where p.user.id=:userId")
	public List<Post> findPostByUserId(Integer userId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM users_saved_post WHERE saved_post_id=:id", nativeQuery = true)
	public void deleteFromUsersSavedPost(@Param("id") Integer id);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM posts_liked WHERE post_id=:id", nativeQuery = true)
	public void deleteFromPostLike(@Param("id") Integer id);
	
	

}
