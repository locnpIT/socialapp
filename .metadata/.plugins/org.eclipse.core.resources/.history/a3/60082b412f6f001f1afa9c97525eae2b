package com.example.socialapp.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.Models.Post;
import com.example.socialapp.Response.ApiResponse;
import com.example.socialapp.Service.PostService;

@RestController
public class PostController {
	
	private final PostService postService;
	
	public PostController(PostService postService) {
		
		this.postService = postService;
	}
	
	
	@PostMapping("/posts/users/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable(name = "userId") Integer userId) throws Exception{
		
		Post createdPost = this.postService.createNewPost(post, userId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdPost);
		
	}
	
	@DeleteMapping("/posts/users/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "postId") Integer postId, @PathVariable(name = "userId") Integer userId) throws Exception{
		
		String message = postService.deletePost(postId, userId);
		ApiResponse res = new ApiResponse(message, true);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable(name = "postId") Integer postId) throws Exception{
		
		Post postFound = this.postService.findPostById(postId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(postFound);
	}
	
	
	@GetMapping("/post/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
		
		List<Post> posts = this.postService.findPostByUserId(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(posts);	
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		
		List<Post> allPosts = this.postService.findAllPost();
		
		return ResponseEntity.status(HttpStatus.OK).body(allPosts);

	}
	
	@PutMapping("/post/{postId}/user/{userId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		
		
		Post posts = this.postService.savePost(postId, userId);
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(posts);
	}
	
	
	@PutMapping("/post/like/{postId}/user/{userId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception{
		
		Post posts = this.postService.likePost(postId, userId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(posts);
	}
	
	
	
	

}
