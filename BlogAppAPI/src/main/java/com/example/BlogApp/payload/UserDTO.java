package com.example.BlogApp.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//we use this class for creating user and all, instead of directly performig on Database entity class(i.e. User)
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {

	private int id;
	
	//not null validation tells the spring to generate exception if we create/update user with no value. 
	@NotNull
	@Size(min = 4, max=100, message="enter proper name ")
	private String name; //this will store the name variable value in the user_name column in database
	
	//for email validation, format is automatically validated using email annptation
	@Email(message="enter proper email")
	private String email;
	
	@NotNull
	@Size(min=3, message="enter proper password with more than 3 characters")
	private String password;
	private String about;
	
}
