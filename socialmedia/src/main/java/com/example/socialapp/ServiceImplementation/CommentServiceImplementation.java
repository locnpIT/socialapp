package com.example.socialapp.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.socialapp.Models.Comment;
import com.example.socialapp.Models.Post;
import com.example.socialapp.Models.User;
import com.example.socialapp.Repository.CommentRepository;
import com.example.socialapp.Repository.PostRepository;
import com.example.socialapp.Service.CommentService;
import com.example.socialapp.Service.PostService;
import com.example.socialapp.Service.UserService;

@Service
public class CommentServiceImplementation implements CommentService {
	
	private final PostService postService;
	
	private final UserService userService;
	
	private final CommentRepository commentRepository;
	
	private final PostRepository postRepository;
	
	
	public CommentServiceImplementation(PostService postService,
			UserService userService,
			CommentRepository commentRepository, 
			PostRepository postRepository) {
		this.postService = postService;
		this.userService = userService;
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		
		User user = this.userService.findUserById(userId);
		
		Post post = this.postService.findPostById(postId);
		
		Comment createdComment = new Comment();
		
		createdComment.setUser(user);
		
		createdComment.setContent(comment.getContent());
		
		createdComment.setCreatedAt(LocalDateTime.now());
		
		Comment savedComment = this.commentRepository.save(createdComment);
		post.getComments().add(savedComment);
		this.postRepository.save(post);
		
		
		return savedComment;
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		
		Comment currentComment = findCommentById(commentId);
		
		User currentUser = this.userService.findUserById(userId);
		
		if(!currentComment.getLiked().contains(currentUser)) {
			
			currentComment.getLiked().add(currentUser);
		
		}else {
			
			currentComment.getLiked().remove(currentUser);			
		
		}
		
		this.commentRepository.save(currentComment);
		
		return currentComment;
	}

	@Override
	public Comment findCommentById(Integer id) throws Exception{
	    Optional<Comment> commentFind = this.commentRepository.findById(id);
		
	    if(commentFind.isEmpty()) {
	    	throw new Exception("Cannot find comment with id = " + id);
	    }
	    
	    Comment currentComment = commentFind.get();
	    
		return currentComment;
	}

	
	
}
