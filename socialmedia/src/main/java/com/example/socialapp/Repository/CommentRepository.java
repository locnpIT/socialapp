package com.example.socialapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.socialapp.Models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	

}
