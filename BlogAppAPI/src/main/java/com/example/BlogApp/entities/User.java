package com.example.BlogApp.entities;
//create User entity in the entities package 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//by default Table name will be same as class name in database, but by using Table annotation we can change the table name in database 
@Table(name="userdata")
//the elow 3 annotations are of lombok and will automatically create getter, setter and constructor.
//thus using lombok no need to mention boilerplate code for getter and setter
@NoArgsConstructor
@Setter
//always keep getter after setter
@Getter
public class User {

	//id means primary key and it will be generated automartically
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//each instance will act as a column name 
	
	//if we wnt to change name of the column in database or perform some conditions on the column we use column annotation
	@Column(name="user_name", nullable=false, length=100 )
	private String name; //this will store the name variable value in the user_name column in database
	private String email;
	private String password;
	private String about;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
				+ "]";
	}
	
	//one user has the list of posts
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Posts> posts = new ArrayList();
	
//once the above is done relauch and check the ppostgres tables we will get the userdata table	
}
