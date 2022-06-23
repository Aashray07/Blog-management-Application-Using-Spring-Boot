package com.example.BlogApp.services;

import java.util.List;

import com.example.BlogApp.payload.CategoryDTO;

public interface CategoryService {

	 CategoryDTO createCategory(CategoryDTO categorydto);
	
	  CategoryDTO updateCategory(CategoryDTO categorydto, int categoryid);
	
	 List<CategoryDTO> getAllCategory();
	
	 CategoryDTO getcategoryById(int categoryid);
	
	 void deleteCategory(int categoryid);
	
	
}
