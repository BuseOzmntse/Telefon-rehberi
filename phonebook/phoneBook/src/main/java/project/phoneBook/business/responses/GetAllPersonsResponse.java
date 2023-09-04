package project.phoneBook.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPersonsResponse {
	private int id;
	private String name;
	private String surname;
	private String title;
	private String location;
	private String number;
}
