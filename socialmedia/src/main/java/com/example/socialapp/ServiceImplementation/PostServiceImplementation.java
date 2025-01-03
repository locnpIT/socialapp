package com.example.socialapp.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.socialapp.Models.Post;
import com.example.socialapp.Models.User;
import com.example.socialapp.Repository.PostRepository;
import com.example.socialapp.Repository.UserRepository;
import com.example.socialapp.Service.PostService;
import com.example.socialapp.Service.UserService;

@Service
public class PostServiceImplementation implements PostService{
	
	
	private final PostRepository postRepository;
	private final UserService userService;
	private final UserRepository userRepository;
	
	
	public PostServiceImplementation(PostRepository postRepository, UserService userService
			, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userService = userService;
		this.userRepository = userRepository;
	}
	
	

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		
		Post newPost = new Post();
		
		User user = this.userService.findUserById(userId);
		
		
		
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		
		return this.postRepository.save(newPost);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(post.getUser().getId() != user.getId()) {
			throw new Exception("User can'r delete another users post!");
		}
		
		postRepository.deleteFromUsersSavedPost(postId);	
		postRepository.deleteFromPostLike(postId);
		
		postRepository.delete(post);
		return "Successful delete post";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception{
		
		Optional<Post> postFind = this.postRepository.findById(postId);
		if(postFind.isPresent()) {
			return postFind.get();
		}
		throw new Exception("Post has id = " + postId + " is not found!");
	}

	@Override
	public List<Post> findAllPost() {
		
		return postRepository.findAll();
	}

	@Override
	public Post savePost(Integer postId, Integer userId) throws Exception{
		
		Post post = findPostById(postId);
		User user = this.userService.findUserById(userId);
		
		
		
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		else {
			user.getSavedPost().add(post);
		}
		
		userRepository.save(user);
		
		return post;
	
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		
		Post post = findPostById(postId);
		User user = this.userService.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);			
		}
		
		return postRepository.save(post);
	}
	
	
	

}
