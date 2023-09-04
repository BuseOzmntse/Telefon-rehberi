package project.phoneBook.business.abstracts;

import java.util.List;

import project.phoneBook.business.requests.CreatePersonRequest;
import project.phoneBook.business.requests.UpdatePersonRequest;
import project.phoneBook.business.responses.GetAllPersonsResponse;
import project.phoneBook.business.responses.GetByIdPersonResponse;

public interface PersonService {
	List<GetAllPersonsResponse> getAll();
	GetByIdPersonResponse getById(int id);
	void add(CreatePersonRequest createPersonRequest);
	void update(UpdatePersonRequest updatePersonRequest);
	void delete(int id);
}
