package com.example.BlogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BlogApp.entities.User;

//using this repository's object we will interact with database  
public interface UserRepo extends JpaRepository<User, Integer>{

}
