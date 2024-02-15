package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.Global.GlobalData;
import com.test.Model.Product;
import com.test.service.CategoryService;
import com.test.service.ProductService;


@Controller
public class HomeController {
	
	@Autowired
	private CategoryService cs;
	
	@Autowired
	private ProductService ps;

	
	@GetMapping({"/", "/home"})
	public String home (Model model) {
	model.addAttribute("cartCount",GlobalData.cart.size());
	return "index";
	}
	@GetMapping ("/shop")
	public String shop (Model model) {
	model.addAttribute("cartCount",GlobalData.cart.size());
	model.addAttribute( "categories",cs.getAllCategory());
	model.addAttribute( "products", ps.getAllProducts());
	return "shop";
	}
	@GetMapping("/shop/category/{id}")
	public String shopByCategory (Model model, @PathVariable int id) {
	model.addAttribute("cartCount",GlobalData.cart.size());
	model.addAttribute("categories", cs.getAllCategory());
	model.addAttribute("products", ps.getProductsByCategoryId(id));
	return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable int id) {
	model.addAttribute("cartCount",GlobalData.cart.size());
	model.addAttribute("product", ps.getProductById(id).get());
	return "viewProduct";
	}
//GlobalData.cart.size()	
	

}
