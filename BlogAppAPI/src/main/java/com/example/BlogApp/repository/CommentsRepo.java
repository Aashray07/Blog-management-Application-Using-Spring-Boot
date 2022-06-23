package com.example.BlogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BlogApp.entities.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {

}
