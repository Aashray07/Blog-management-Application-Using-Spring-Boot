package com.example.BlogApp.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlogApp.payload.UserDTO;
import com.example.BlogApp.services.UserService;

@RestController
@RequestMapping("/api/Users")
public class UserController {

	//we use UserDTO because we dont want the User entity to get exposed directly. That is not a good coding convention
	//by creating user service class it makes our controller code look cleaner
	@Autowired
	private UserService userservice;
	
	//when there is a post request it will go to method having postmapping
	@PostMapping("/create")
	//This method will create the user. Client will send the post request using POSTMAN with their details in JSON format and that request will be tackled by UserController's createUser().
	//request body means the data passed in form of request will be stored in userdto parameter 
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto){
		
		//calls the createuser method of userService class
		UserDTO new_user_dto = userservice.createUser(userdto);
		
		//returns the updated result and store the object in Users table
		return new ResponseEntity<>(new_user_dto, HttpStatus.CREATED);
		
		
	}
	
	
	//we have to mention path as localhost:8080/api/Users/update/2 and write the updated object with put request to update the old object with id 2
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userdto, @PathVariable("userId") int userId){
		
		UserDTO updated_user  = userservice.updateUser(userdto, userId);
		return ResponseEntity.ok(updated_user);
	}
	
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> DeleteUser(@PathVariable("userId") int userid){
		
		userservice.deleteUser(userid);
		return new ResponseEntity(Map.of("message", "user deleted successfully"), HttpStatus.OK);
	
	}
	
	//get request by client is made to view/get all the users from the database 
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(userservice.getAllUser());
	}
	
	//if a client want to see a single user using userid they can use get request with path api/users/userid to view that user
	@GetMapping("view/{userId}")
	public ResponseEntity<UserDTO> getUserByID(@PathVariable("userId") int userId){
		return ResponseEntity.ok(userservice.getUserbyID(userId));
	}
}
