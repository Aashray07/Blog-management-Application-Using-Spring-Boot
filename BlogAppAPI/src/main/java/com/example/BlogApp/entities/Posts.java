package com.example.BlogApp.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Posts")
@NoArgsConstructor
@Setter
@Getter
public class Posts {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postid;
	
	private String title;
	
	private String content;
	
	private String imagename;
	
	private Date addeddate;
	
	//we want our post to be associated with in a Category
	// the post will be assigned to the category mentioned in this variable
	//as there can be more than one post object in a single category we write manytoone here
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;
	//after this go to Category entity and there create onetomany relationship as one category can have multiple posts
	
	//similarly do for user as one use4r can have multuiple posts
	@ManyToOne
	@JoinColumn(name="userid")
	private User user; 
	
	//whenever we write one to many then this one post will return many items(lists of items), thus we create a list
	@OneToMany
	private List<Comments> comments = new ArrayList<Comments>();
	
	
	//in the Posts table also in postgres we can find userid and category id column automatically added
	
	
	
}
