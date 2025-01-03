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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.Models.Post;
import com.example.socialapp.Models.User;
import com.example.socialapp.Response.ApiResponse;
import com.example.socialapp.Service.PostService;
import com.example.socialapp.Service.UserService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	private final PostService postService;
	private final UserService userService;
	
	public PostController(PostService postService, UserService userService) {
		this.postService = postService;
		this.userService = userService;
	}
	
	
	@PostMapping("/posts/users")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt) throws Exception{
		
		User reqUser = this.userService.findUserByJwt(jwt);
		
		
		Post createdPost = this.postService.createNewPost(post, reqUser.getId());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdPost);
		
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "postId") Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		
		User reqUser = this.userService.findUserByJwt(jwt);
		String message = postService.deletePost(postId, reqUser.getId());
		ApiResponse res = new ApiResponse(message, true);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable(name = "postId") Integer postId) throws Exception{
		
		Post postFound = this.postService.findPostById(postId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(postFound);
	}
	
	
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
		
		List<Post> posts = this.postService.findPostByUserId(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(posts);	
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		
		List<Post> allPosts = this.postService.findAllPost();
		
		return ResponseEntity.status(HttpStatus.OK).body(allPosts);

	}
	
	@PutMapping("/save/posts/{postId}")
	public ResponseEntity<Post> savedPostHandler(
			@PathVariable Integer postId,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User reqUser = this.userService.findUserByJwt(jwt);
		
		Post posts = this.postService.savePost(postId, reqUser.getId());
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(posts);
	}
	
	
	@PutMapping("/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(
			@PathVariable Integer postId,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User reqUser = this.userService.findUserByJwt(jwt);
		
		Post posts = this.postService.likePost(postId, reqUser.getId());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(posts);
	}
	
	
	
	

}
