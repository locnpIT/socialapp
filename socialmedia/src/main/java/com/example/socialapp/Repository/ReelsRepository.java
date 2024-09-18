package com.example.socialapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.socialapp.Models.Reels;


@Repository
public interface ReelsRepository extends JpaRepository<Reels, Long> {
	
	
	public List<Reels> findByUserId(Integer userId);
	

	

}
