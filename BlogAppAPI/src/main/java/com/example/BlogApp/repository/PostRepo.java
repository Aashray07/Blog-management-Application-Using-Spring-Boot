package com.example.BlogApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BlogApp.entities.Category;
import com.example.BlogApp.entities.Posts;
import com.example.BlogApp.entities.User;

public interface PostRepo extends JpaRepository<Posts, Integer> {

	//this are the custom methods using which we could get the list of posts for a single user or category. 
	List<Posts> findByUser(User user);
	
	List<Posts> findByCategory(Category category);
	
	//this method is used to seacrch for all the post objects which contains the string passed as argument in its title.
	List<Posts> findByTitleContaining(String title);
	
}
