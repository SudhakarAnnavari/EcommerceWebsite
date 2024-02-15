package com.test.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.Model.Category;
import com.test.Model.Product;
import com.test.dlo.ProductDTO;
import com.test.service.CategoryService;
import com.test.service.ProductService;

@Controller
public class AdminController {
	
	@Autowired
	private CategoryService cs;
	
	@Autowired
	private ProductService ps;
	
	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";

    
    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }
    
    @GetMapping("/admin/categories")
    public String categories(Model model) {
    	model.addAttribute("categories",cs.getAllCategory());
        return "categories";
    }
    
    @GetMapping("/admin/categories/add")
    public String getcategories(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }
    
    @PostMapping("/admin/categories/add")
    public String postcategories(@ModelAttribute("category")Category category ) {
        cs.addCategory(category);
        return "redirect:/admin/categories";  
    }
    
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable("id") int id) {
    	cs.removeCategory(id);
		return "redirect:/admin/categories";
    }
    
    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id,Model m) {
		Optional<Category> category = cs.getCategory(id);
    	if(category.isPresent()) {
    		m.addAttribute("category",category.get());
    		return "categoriesAdd";
    	}
    	else {
    		return "404";
    	}   	    	
    }
    
//    products
    
    @GetMapping("/admin/products")
    public String getAllProducts(Model m) {
    	m.addAttribute("products",ps.getAllProducts());
		return "products";
    }
    
    @GetMapping("/admin/products/add")
    public String AddProduct(Model m) {
    	m.addAttribute("productDTO",new ProductDTO());
    	m.addAttribute("categories",cs.getAllCategory());
		return "productsAdd";
    }
    
    @PostMapping ("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
                                  @RequestParam("productImage")MultipartFile file,
                                  @RequestParam("imgName")String imgName)throws IOException {
									
    	Product product = new Product();
        product.setId (productDTO.getId());
        product.setName (productDTO.getName());
        product.setCategory (cs.getCategory(productDTO.getCategoryId()).get());
        product.setPrice (productDTO.getPrice());
        product.setWeight (productDTO.getWeight());
        product.setDescription (productDTO.getDescription());
        String imageUUID;
        if(!file.isEmpty()) {
        imageUUID = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
        Files.write(fileNameAndPath, file.getBytes());
        } 
        else {
        imageUUID = imgName;
        }
        
        product.setImageName(imageUUID);
        ps.addProduct(product);
        
    	return "redirect:/admin/products";
    	
    }
    
    @GetMapping("/admin/product/delete/{id}")
    private String deleteProduct(@PathVariable int id) {
    	ps.removeProduct(id);
    	return "redirect:/admin/products";
    }
    
    @GetMapping("/admin/product/update/{id}")
    public String updateProductGet (@PathVariable int id, Model model) {
    Product product = ps.getProductById(id).get();
    ProductDTO productDTO = new ProductDTO();
    productDTO.setId (product.getId());
    productDTO.setName (product.getName());
    productDTO.setCategoryId((product.getCategory().getId()));
    productDTO.setPrice (product.getPrice());
    productDTO.setWeight((product.getWeight()));
    productDTO.setDescription (product.getDescription());
    productDTO.setImageName (product.getImageName());
    model.addAttribute( "categories", cs.getAllCategory());
    model.addAttribute( "productDTO", productDTO);
    return "productsAdd";
    }
    
    
    
}
