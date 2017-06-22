package edu.mum.coffee.ws;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;

@RestController
public class PersonRestController {

	@Autowired
	private PersonService personService;

	@PostMapping("/Person")
	public Person savePerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}

	@GetMapping("/Person/findByEmail/{email}")
	public @ResponseBody List<Person> findByEmail(@PathVariable("email")String email) {
		return personService.findByEmail(email);
	}

	@GetMapping("/Person/findById/{id}")
	public Person findById(@PathVariable("id")Long id) {
		return personService.findById(id);
	}

	@DeleteMapping("/Person/delete")
	public void removePerson(@RequestBody Person person) {
		personService.removePerson(person);

	}
}
