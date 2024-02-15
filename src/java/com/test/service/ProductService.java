package com.test.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Model.Product;
import com.test.Repositary.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts(){
		return (List<Product>) productRepo.findAll();
	}
	
	public void addProduct(Product product) {
		productRepo.save(product);
	}
	
	public void removeProduct(int id) {
		productRepo.deleteById(id);
	}
	
	public Optional<Product> getProductById(int id){
		Optional<Product> byId = productRepo.findById(id);
		return byId;
	}
	
	public List<Product> getProductsByCategoryId(int id){
		return productRepo.findAllByCategory_Id(id);
	}

}
