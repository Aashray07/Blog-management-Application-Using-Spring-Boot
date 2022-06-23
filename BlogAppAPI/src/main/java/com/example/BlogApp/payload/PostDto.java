package com.example.BlogApp.payload;

import java.util.Date;
import java.util.List;

import com.example.BlogApp.entities.Category;
import com.example.BlogApp.entities.Comments;
import com.example.BlogApp.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	
	private int postid;

	//we will set image and date directly in service implementation, and we get userid and catid in service implimentation too
private String title;
	
	private String content;
	
private String imagename;
	
	private Date addeddate;
	
	private CategoryDTO category;
	
	private UserDTO user; 
	
	//by writing this we will get a comments list at the end of object 
	//we dont want create a seprate URI to access all the comments using postid in URI, instead we want all the comments with the post itself when we get the posts.
	//thats why we dont use get methods for comments in its controller we will get all the comments for the post here only.
	private List<CommentsDTO> comments;
	//we create list of CommentsDTO instead of comments, otherwise when w3e get the post we will run in infinite loop
	
}
