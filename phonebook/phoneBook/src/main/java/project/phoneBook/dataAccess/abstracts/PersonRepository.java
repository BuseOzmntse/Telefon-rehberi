package project.phoneBook.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.phoneBook.entities.concretes.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{
	boolean existsByNumber(String number);
}
//eklendi