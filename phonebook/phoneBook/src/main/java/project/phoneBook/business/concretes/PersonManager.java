package project.phoneBook.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.phoneBook.business.abstracts.PersonService;
import project.phoneBook.business.requests.CreatePersonRequest;
import project.phoneBook.business.requests.UpdatePersonRequest;
import project.phoneBook.business.responses.GetAllPersonsResponse;
import project.phoneBook.business.responses.GetByIdPersonResponse;
import project.phoneBook.business.rules.PersonBusinessRules;
import project.phoneBook.core.utilities.mappers.ModelMapperService;
import project.phoneBook.dataAccess.abstracts.PersonRepository;
import project.phoneBook.entities.concretes.Person;

@Service
@AllArgsConstructor
public class PersonManager implements PersonService{
	private PersonRepository personRepository;
	private ModelMapperService modelMapperService;
	private PersonBusinessRules personBusinessRules;

	@Override
	public List<GetAllPersonsResponse> getAll() {
		//rules
		List<Person> persons = personRepository.findAll();
		
		List<GetAllPersonsResponse> personsResponse = persons.stream().map(person->this.modelMapperService.forResponse().map(person, GetAllPersonsResponse.class)).collect(Collectors.toList());
		
		return personsResponse;
	}

	@Override
	public void add(CreatePersonRequest createPersonRequest) {
		this.personBusinessRules.checkIfPersonNumberExists(createPersonRequest.getNumber());
		
		Person person = this.modelMapperService.forRequest().map(createPersonRequest, Person.class);
				
		this.personRepository.save(person);
		
	}

	@Override
	public GetByIdPersonResponse getById(int id) {
		Person person = this.personRepository.findById(id).orElseThrow();
		
		GetByIdPersonResponse response = this.modelMapperService.forResponse().map(person, GetByIdPersonResponse.class);
		
		return response;
	}

	@Override
	public void update(UpdatePersonRequest updatePersonRequest) {
		Person person = this.modelMapperService.forRequest().map(updatePersonRequest, Person.class);
		this.personRepository.save(person);
	}

	@Override
	public void delete(int id) {
		this.personRepository.deleteById(id);
		
	}
	
}
