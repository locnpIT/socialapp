package com.example.socialapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.Models.Comment;
import com.example.socialapp.Models.User;
import com.example.socialapp.Service.CommentService;
import com.example.socialapp.Service.UserService;

@RestController
@RequestMapping("/api")
public class CommentController {

	private final CommentService commentService;
	private final UserService userService;
	
	
	public CommentController(CommentService commentService, UserService userService) {
		this.commentService = commentService;
		this.userService = userService;
	}
	
	
	
	@GetMapping("/comments/{id}")
	public Comment getComment(@PathVariable("id") Integer id) throws Exception {
	
		return this.commentService.findCommentById(id);
	}
	
	
	@PostMapping("/comments/post/{postId}")
	public Comment createComment(@RequestBody Comment comment,
			@PathVariable(name="postId") Integer postId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User reqUser = this.userService.findUserByJwt(jwt);
		
		Comment createdComment = this.commentService.createComment(comment, postId, reqUser.getId());
		
	
		return createdComment;
	}
	
	@PutMapping("/comments/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt, 
			@PathVariable(name = "commentId") Integer commentId) throws Exception {
		
		User reqUser = this.userService.findUserByJwt(jwt);
			
		Comment likedComment = this.commentService.likeComment(commentId, reqUser.getId());
		
		return likedComment;
	
	}
	
	
	 
	
	
}
