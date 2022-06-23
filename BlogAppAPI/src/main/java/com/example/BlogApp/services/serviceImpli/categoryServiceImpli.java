package com.example.BlogApp.services.serviceImpli;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BlogApp.entities.Category;
import com.example.BlogApp.entities.User;
import com.example.BlogApp.payload.CategoryDTO;
import com.example.BlogApp.payload.UserDTO;
import com.example.BlogApp.repository.CategoryRepo;
import com.example.BlogApp.services.CategoryService;

@Service
public class categoryServiceImpli implements CategoryService {

	@Autowired
	private CategoryRepo catrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categorydto) {
		// TODO Auto-generated method stub
		
		Category cat = modelmapper.map(categorydto, Category.class);
		Category added_cat = catrepo.save(cat);
		CategoryDTO cdto = modelmapper.map(added_cat, CategoryDTO.class); 
		
		return cdto;
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categorydto, int categoryid) {
		// TODO Auto-generated method stub
		
		Category cat = catrepo.findById(categoryid).orElse(new Category());
		
		cat.setCategorydetails(categorydto.getCategorydetails());
		cat.setCategorytitle(categorydto.getCategorytitle());
		
		Category savedcat = catrepo.save(cat);
						
		return modelmapper.map(savedcat, CategoryDTO.class);

	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		// TODO Auto-generated method stub
		
		List<Category> cats = catrepo.findAll();
		
		//copy entire list of object from cats to catdtos
		List<CategoryDTO> catdtos = cats.stream().map((cat)->modelmapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		
		return catdtos;
	}

	@Override
	public CategoryDTO getcategoryById(int categoryid) {
		// TODO Auto-generated method stub
		
		//by default when we do findbyid spring returns object in form of category object.
		//thats why we cant directly do categoryDTO cat = catrepo.findbyid();
		Category cat = catrepo.findById(categoryid).orElse(new Category());
		
		return modelmapper.map(cat, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(int categoryid) {
		// TODO Auto-generated method stub
		Category cat = catrepo.findById(categoryid).orElse(new Category());
		catrepo.delete(cat);

	}

}
