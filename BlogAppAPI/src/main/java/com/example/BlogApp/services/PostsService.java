package com.example.BlogApp.services;

import java.util.List;

import com.example.BlogApp.payload.CategoryDTO;
import com.example.BlogApp.payload.PostDto;

public interface PostsService {

	//here the postdto object parameter is when client sends the post request in JSON format for the blog post, the data will be stored in postdto object
	//The post is created by a specific user and for a specific category, thus w3e mention their respective ids in the parameter too.
	PostDto createPost(PostDto postdto, int userid, int catid);
	
	  PostDto updatePost(PostDto postdto, int postid);
	
	 List<PostDto> getAllPosts(int pagesize, int pagenum, String sortBy);
	
	 PostDto getPostById(int postid);
	
	 void deletePost(int postid);
	 
	 
	 //in the database we can find 2 extra column which is categoryid and userid which defines the user and category related to that post
	 //so if we want to view the posts for a particular category use categoryid
	 List<PostDto> getPostByCategory(int categoryid);
	 
	 List<PostDto> getPostByUser(int userid);
	 
	 //we can also create a method for searching a post using keyword
	 List<PostDto> searchposts(String keyword);
	
}
