package com.example.BlogApp.services;

import java.util.List;

import com.example.BlogApp.payload.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO user);
	
	UserDTO updateUser(UserDTO user, int userid);
	
	UserDTO getUserbyID(int userid);
	
	List<UserDTO> getAllUser();
	
	void deleteUser(int userid);
}
