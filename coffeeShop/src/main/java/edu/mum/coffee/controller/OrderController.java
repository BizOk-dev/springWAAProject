package edu.mum.coffee.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@Controller
public class OrderController {
    List<Orderline> list=new ArrayList<>();
    int productId;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	ProductService productService;
	
	Order order;
		
	
	@GetMapping("/order")
	public String add(@ModelAttribute Person person,ModelMap model){
		model.addAttribute("persons", personService.getAllPerson());
		return "addOrder";
	}
	
	@GetMapping("/orderLine")
	public String addline(@ModelAttribute Product product, Model model){
		List<Product> productList = productService.getAllProduct();
		//productId=id;
		model.addAttribute("productList", productList);
		//model.addAttribute("orderline", orderline);
		return "addOrderLine";
	}
	
	@PostMapping("/newOrder")
	public String addOrder(@RequestParam("personId")long id,
						   @ModelAttribute  Person person, ModelMap model){
	    order=new Order();
		System.out.println(person.getId());
		order.setPerson(personService.findById(id));
		order.setOrderDate(new Date());
		orderService.save(order);
		model.addAttribute("orderDetail", order);
		return "OrderDetail";
	}
	
	
	@PostMapping("/newOrderLine")
	public String addOrderLine(/*@RequestParam("quantity") int quantity*/ 
							   @RequestParam("productId")long id,
							   @ModelAttribute  Product product, ModelMap model){
		Orderline orderline=new Orderline();
		orderline.setProduct(productService.getProduct(productId));
		//orderline.setQuantity(quantity);
		orderline.setOrder(order);
		list.add(orderline);
		model.addAttribute("orderList", orderService.findAll());
		return "home";
	}
	
	@GetMapping("/orderLine/view")
	public String getProduct(ModelMap model,@RequestParam("id")int orderId) {
		order= orderService.findById(orderId);
		model.addAttribute("orderlinedetail",order.getOrderLines());
		return "orderlineDetails";
	}
	
	@PostMapping("/addOrder")
	
	public String save(@ModelAttribute Order order){
		orderService.save(order);
		return "redirect:/OrderList";
	}
	
	
	@GetMapping("/orderlist")
	public String findAll(Model model){
		model.addAttribute("displayOrder", orderService.findAll());
		return "OrderList";
	}
	
}















/*@PostMapping("/orderLine")
public void addline(@RequestParam("productId") int id,
					@RequestParam("quantity") int quantity, ModelMap model){
	Orderline orderline = new Orderline();
	orderline.setProduct(productService.getProduct(id));
	orderline.setQuantity(quantity);
	orderline.setOrder(order);
	list.add(orderline);
	//System.out.println(list.get(0));
	model.addAttribute("orderlinelist", list);
	for(Orderline o : list){
		System.out.println(o);
	}
	//return "addOrder";
}*/

/*@GetMapping({ "/addOrderLine/{id}" })
public String addOrderLine(@PathVariable int id, Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String username = auth.getName();
	System.out.println("add");
	System.out.println(username);
	List<Product> productList = productService.getAllProduct();
	model.addAttribute("productList", productList);
	model.addAttribute("order", orderService.findById(id));
	return "addOrderLine";
}

@GetMapping({ "/createOrderLine/{id}" })
public String createOrderLine(@PathVariable int id, @RequestParam("quantity") int quantity,
		@RequestParam("product") int productID, Model model) {
	System.out.println("creating orderLine");
	System.out.println("id:" + id);
	System.out.println("quantity:" + quantity);
	System.out.println("productId" + productID);
	Orderline orderLine = new Orderline();
	orderLine.setOrder(orderService.findById(id));
	orderLine.setProduct(productService.getProduct(productID));
	orderLine.setQuantity(quantity);
	orderService.findById(id).addOrderLine(orderLine);
	List<Order> orderList = orderService.findAll();
	model.addAttribute("orderList", orderList);
	return "orderList";
}
@RequestMapping(value = "/orderProductUser/{id}",method = RequestMethod.POST)
public String orderProductUser(@PathVariable int id, @RequestParam("quantity") int quantity,
		@RequestParam("product") int productID, Model model) {
	Orderline orderLine = new Orderline();
	orderLine.setOrder(orderService.findById(id));
	orderLine.setProduct(productService.getProduct(productID));
	orderLine.setQuantity(quantity);
	orderService.findById(id).addOrderLine(orderLine);
	
	List<Order> orderList = orderService.findAll();
	model.addAttribute("orderList", orderList);
	return "userPage";
}

@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
public String createOrder(@RequestParam("personId") Long personId, Model model) {
	System.out.println("creating order");
	System.out.println(personId);
	Order order = new Order();
	order.setOrderDate(new Date());
	order.setPerson(personService.findById(personId));
	orderService.save(order);
	List<Order> orderList = orderService.findAll();
	model.addAttribute("orderList", orderList);
	return "orderList";
}

@RequestMapping(value = "/orderList", method = RequestMethod.GET)
public String addProductPage(Model model) {
	List<Order> orderList = orderService.findAll();
	model.addAttribute("orderList", orderList);
	return "orderList";
}

@RequestMapping(value = "/makeOrderPage", method = RequestMethod.GET)
public String makeOrderPage(Model model) {
	Order order = new Order();
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String username = auth.getName();
	List<Order> orderList = orderService.findAll();
	boolean find = false;
	for(Order o: orderList){
		/*if(o.getPerson().getUser().getUsername().equals(username)){
			model.addAttribute("order", o);
			find = true;
		//}
	}
	if(!find){
	List<Person> personList = personService.getAllPerson();
	for(Person person : personList){
	//if(person.getUser().getUsername().equals(username)){
		order.setOrderDate(new Date());
		order.setPerson(person);
		orderService.save(order);
	}
	}
	model.addAttribute("order", order);
	//}
	List<Product> productList = productService.getAllProduct();
	model.addAttribute("productList", productList);
	return "orderProduct";
}

@GetMapping({ "/createOrderLineCustomer" })
public String createNewOrder(@RequestParam("quantity") int quantity, @RequestParam("product") int productID,
		Model model) {

	List<Order> orderList = orderService.findAll();
	model.addAttribute("orderList", orderList);
	return "orderList";
}*/


