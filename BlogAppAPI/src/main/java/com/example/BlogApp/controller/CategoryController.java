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
import org.springframework.web.bind.annotation.RestController;

import com.example.BlogApp.entities.Category;
import com.example.BlogApp.payload.CategoryDTO;
import com.example.BlogApp.payload.UserDTO;
import com.example.BlogApp.services.CategoryService;

@RestController
@RequestMapping("api/category")
public class CategoryController {

	@Autowired
	private CategoryService cservice;
	
	//post
	@PostMapping("/create")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO cdto){
		
		CategoryDTO createddto = cservice.createCategory(cdto);
		
		return new ResponseEntity<>(createddto, HttpStatus.CREATED);
	}
	
	//put
	@PutMapping("/update/{catid}")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO catdto, @PathVariable("catid") int cid){
		
		CategoryDTO cdto = cservice.updateCategory(catdto, cid);
		
		return ResponseEntity.ok(cdto);
	}
	
	//delete
	@DeleteMapping("/delete/{catid}")
	public ResponseEntity<?> DeleteCategory(@PathVariable("catid") int catid){
		
		cservice.deleteCategory(catid);
		
		return new ResponseEntity(Map.of("message", "Category deleted successfully"), HttpStatus.OK);
	
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllcategory(){
		return ResponseEntity.ok(cservice.getAllCategory());
	}
	
	@GetMapping("/{catid}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("catid") int catid){
		CategoryDTO cdto = cservice.getcategoryById(catid);
		
		return ResponseEntity.ok(cdto);
	}
	
	
	
}
