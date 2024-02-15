package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Model.Category;
import com.test.Repositary.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo repo;
	
	public List<Category> getAllCategory(){
		return (List<Category>) repo.findAll();
	}
	
	public void addCategory(Category category) {
		repo.save(category);
	}
	
	public void removeCategory(int id) {
		repo.deleteById(id);
	}
	
	public Optional<Category> getCategory(int id) {
		Optional<Category> category = repo.findById(id);
		return category;
	}


}
