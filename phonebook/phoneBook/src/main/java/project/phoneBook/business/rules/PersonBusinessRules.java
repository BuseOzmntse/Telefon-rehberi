package project.phoneBook.business.rules;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.phoneBook.core.utilities.exceptions.BusinessException;
import project.phoneBook.dataAccess.abstracts.PersonRepository;

@AllArgsConstructor
@Service
public class PersonBusinessRules {
	private PersonRepository personRepository;
	
	public void checkIfPersonNumberExists(String number) {
		if(this.personRepository.existsByNumber(number)) {
			throw new BusinessException("This number already exists");
		}
	}
}
