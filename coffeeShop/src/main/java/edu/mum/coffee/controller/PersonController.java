package edu.mum.coffee.controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.PersonService;

@Controller
public class PersonController {

	long id;
	@Autowired
	private PersonService personService;
		
	@GetMapping("/person")
	public String add(@ModelAttribute Person person){
		return "addPerson";
	}
	@PostMapping("/addperson")
	public String save(@ModelAttribute Person person){
		personService.savePerson(person);
		return "redirect:/PersonList";
	}
	
	@GetMapping("/person/{id}")
	public String findById(@PathVariable("id")long id, Model model) {
		 model.addAttribute("persondata", personService.findById(id));
		return "PersonList";
	}
	
	@GetMapping("/person/email/{email}")
	public String findbyEmail(@PathVariable("email")String email ,Model model){
		model.addAttribute("displayPerson", personService.findByEmail(email));
		return "PersonList";
	}
	
	@GetMapping("/person/delete")
	public String removePerson(@RequestParam("id") long personId) {
		Person person=personService.findById(personId);
		personService.removePerson(person);
		return "redirect:/PersonList";
	}
	
	@GetMapping("/personlist")
	public String getAllPerson(Model model) {
		model.addAttribute("displayPerson", personService.getAllPerson());
		return  "PersonList" ;
	}
	
	@GetMapping("/person/view")
	public String getProduct(ModelMap model,@RequestParam("id")long personId) {
		Person	person= personService.findById(personId);
		model.addAttribute("persondetail",person);
		return "personDetail";
	}

	@GetMapping("/person/edit")
	public String editPerson(ModelMap model,@RequestParam("id")long personId) {
		id=personId;
		Person	person= personService.findById(personId);
		model.addAttribute("personedit",person);
		return "editPerson";
	}
	
	@GetMapping("/person/update")
	public String update(@ModelAttribute Person person) {
		Person person1=personService.findById(id);
		Address address=person1.getAddress();
		person1.setEmail(person.getEmail());
		person1.setFirstName(person.getFirstName());
		person1.setLastName(person.getLastName());
		person1.setPhone(person.getPhone());
		address.setCity(person.getAddress().getCity());
		address.setState(person.getAddress().getState());
		address.setCountry(person.getAddress().getCountry());
		address.setZipcode(person.getAddress().getZipcode());
		person1.setAddress(address);
		personService.savePerson(person1);
		return "redirect:/PersonList";
	}
}
