package com.example.BlogApp.entities;

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
@Table(name="Categories")
@NoArgsConstructor
@Setter
@Getter
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categoryid;
	
	@Column(name="Title", nullable=false, length=100)
	private String Categorytitle;
	
	@Column(name="Details", nullable=false, length=100)
	private String categorydetails;

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", Categorytitle=" + Categorytitle + ", categorydetails="
				+ categorydetails + "]";
	}
	
	//one category can have multiple posts thus here we mention oneto many rlationship
	//in mappedby we write the variable name from post class in which the category will be stored 
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Posts> posts = new ArrayList();
	
	
}
