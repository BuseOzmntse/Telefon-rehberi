package project.phoneBook.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import project.phoneBook.business.abstracts.PersonService;
import project.phoneBook.business.requests.CreatePersonRequest;
import project.phoneBook.business.requests.UpdatePersonRequest;
import project.phoneBook.business.responses.GetAllPersonsResponse;
import project.phoneBook.business.responses.GetByIdPersonResponse;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class PersonsController {
	private PersonService personService;

	@GetMapping()
	public List<GetAllPersonsResponse> getAll(){
		return personService.getAll();
	}
	
	@GetMapping("/{id}")
	public GetByIdPersonResponse getById(@PathVariable int id) {
		return personService.getById(id);
	}
	
	@PostMapping()
	@ResponseStatus(code=HttpStatus.CREATED)
	public void add(@RequestBody() @Valid() CreatePersonRequest createPersonRequest) {
		this.personService.add(createPersonRequest);
	}
	
	@PutMapping
	public void update(@RequestBody() UpdatePersonRequest updatePersonRequest) {
		this.personService.update(updatePersonRequest);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		this.personService.delete(id);
	}
}
