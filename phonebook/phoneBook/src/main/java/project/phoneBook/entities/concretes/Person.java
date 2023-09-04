package project.phoneBook.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="persons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
	@Id //PK 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="title")
	private String title;
	
	@Column(name="location")
	private String location;
	
	@Column(name="number", unique = true)
	private String number;
	
}
