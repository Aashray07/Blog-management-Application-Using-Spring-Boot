package com.example.BlogApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Comments")
@NoArgsConstructor
@Setter
@Getter
public class Comments {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentid;
	
	//contenty of the comment
	private String content;
	
	//always think of the class which  we are in as first and then think relation. here we are in comments class which has many to one relationship with Posts.
	//same way in posts we write one to many. As we think posts as first and one post can have mujltiple comments
	@ManyToOne
	private Posts post;
	
}
