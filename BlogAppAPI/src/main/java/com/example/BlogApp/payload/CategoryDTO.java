package com.example.BlogApp.payload;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDTO {

private int categoryid;
	
	private String Categorytitle;
	
	
	private String categorydetails;
}
