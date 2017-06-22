package edu.mum.coffee.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.ProductService;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
		
	@PostMapping("/Product")
	public Product save(@RequestBody Product product) {
		return productService.save(product);
	}

	@DeleteMapping("/Product")
	public void delete(@RequestBody Product product) {
		productService.delete(product);
	}

	@GetMapping("/Product/{id}")
	public Product getProduct(@PathVariable("id")int productId) {
		return  productService.getProduct(productId);
	}

	@GetMapping("/Product")
	public @ResponseBody List<Product> getAllProduct() {
		return  productService.getAllProduct() ;
	}
	
	@GetMapping("/Product/findByText/{criteria}")
	public @ResponseBody List<Product> findByTextSearch(@PathVariable("criteria")String criteria) {
		return productService.findByTextSearch(criteria);
	}

	@GetMapping("/Product/findByPrice/{minPrice}/{maxPrice}")
	public @ResponseBody List<Product> findByPrice(@PathVariable("minPrice")double minPrice, @PathVariable("maxPrice")double maxPrice) {
		return  productService.findByPrice(minPrice, maxPrice);
	}
	
	@GetMapping("/Product/findByType/{type}")
	public @ResponseBody List<Product> findByProductType(@PathVariable("type")ProductType productType) {
		 return productService.findByProductType(productType);
	}
	
	@PutMapping(value="/update/{id}")
	public Product updateProductPost(@PathVariable int id,@RequestBody Product product){
		Product objProduct = productService.getProduct(id);
		objProduct.setDescription(product.getDescription());
		objProduct.setProductName(product.getProductName());
		objProduct.setPrice(product.getPrice());
		objProduct.setProductType(product.getProductType());
		productService.save(objProduct);
		return objProduct;
	}

}
