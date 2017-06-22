package edu.mum.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.ProductService;

@Controller
public class ProductController {

	int id;
	@Autowired
	private ProductService productService;
	
	@GetMapping({"/addProduct"})
	public String save1(@ModelAttribute Product product) {
		return "addProduct";
	}
	@PostMapping("/product")
	public String save(@ModelAttribute Product product) {
		productService.save(product);
		return "ProductList";
	}

	@GetMapping("/product/delete")
	public String delete(@RequestParam("id")int productId) {
		productService.delete(productService.getProduct(productId));
		return "redirect:/product";
	}

	@GetMapping("/product/view")
	public String getProduct(ModelMap model,@RequestParam("id")int productId) {
		Product	product= productService.getProduct(productId);
		model.addAttribute("productdetail",product);
		return "productDetail";
	}


	@GetMapping("/product/edit")
	public String editProduct(ModelMap model,@RequestParam("id")int productId) {
		id=productId;
		Product	product= productService.getProduct(productId);
		model.addAttribute("productedit",product);
		return "editProduct";
	}
	
	@GetMapping("/product/update")
	public String update(@ModelAttribute Product productedit) {
		Product product1=productService.getProduct(id);
		product1.setDescription(productedit.getDescription());
		product1.setPrice(productedit.getPrice());
		product1.setProductName(productedit.getProductName());
		productService.save(product1);
		return "redirect:/product";
	}
	
	@GetMapping("/product")
	public String getAllProduct(Model model) {
		model.addAttribute("displayProduct", productService.getAllProduct());
		return  "ProductList" ;
	}
	
	@GetMapping("/product/search/{criteria}")
	public List<Product> findByTextSearch(@PathVariable("criteria")String criteria) {
		return productService.findByTextSearch(criteria);
	}

	@GetMapping("/product/{minPrice}/{maxPrice}")
	public List<Product> findByPrice(@PathVariable("minPrice")double minPrice, @PathVariable("maxPrice")double maxPrice) {
		return  productService.findByPrice(minPrice, maxPrice);
	}
	
	@GetMapping("/product/type/{type}")
	public List<Product> findByProductType(@PathVariable("type")ProductType productType) {
		 return productService.findByProductType(productType);
	}

}