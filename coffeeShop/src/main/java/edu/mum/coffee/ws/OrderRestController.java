package edu.mum.coffee.ws;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;

@RestController
public class OrderRestController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/Order")
	public Order save(@RequestBody Order order){
		return orderService.save(order);
	}
	
	@DeleteMapping("/Order/Delete")
	public void delete(@RequestBody Order order){
		orderService.delete(order);
	}
	
	@GetMapping("/Order/ByProduct")
	public @ResponseBody List<Order> findByProduct(@RequestBody Product product) {
		return orderService.findByProduct(product);
	}
	
	@GetMapping("/Order/ByPerson")
	public @ResponseBody List<Order> findByPerson(@RequestBody Person person) {
		return orderService.findByPerson(person);
	}
	
	@GetMapping("/Order/ByDate/{minDate}/{maxDate}")
	public @ResponseBody List<Order> findByDate(@PathVariable("minDate")Date minDate, @PathVariable("maxDate")Date maxDate) {
		return orderService.findByDate(minDate, maxDate);
	}
	
	@GetMapping("/Order/{id}")
	public Order findById(@PathVariable("id")int id){
		return orderService.findById(id);
	}
	
	@GetMapping("/Order")
	public @ResponseBody List<Order> findAll(){
		return orderService.findAll();
	}

}
