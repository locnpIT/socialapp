package com.example.socialapp.Service;

import org.springframework.stereotype.Service;

import com.example.socialapp.Models.Comment;
import com.example.socialapp.Repository.CommentRepository;


public interface CommentService{
	
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;
	
	public Comment likeComment(Integer commentId, Integer userId) throws Exception;
	
	public Comment findCommentById(Integer id) throws Exception;

}
