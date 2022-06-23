package com.example.BlogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BlogApp.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
