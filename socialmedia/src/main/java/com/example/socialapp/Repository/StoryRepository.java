package com.example.socialapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.socialapp.Models.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
	
	public List<Story> findByUserId(Integer userId);

}
