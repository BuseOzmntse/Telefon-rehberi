package project.phoneBook.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {
	
	@NotNull
	@NotBlank
	private String name;
	
	private String surname;
	private String title;
	private String location;
	
	@NotNull
	@NotBlank
	private String number;
}
