package digital.paisley.phonebook.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    public int id;
    @Email(message = "Please, check entered email is correct",
            regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,10}$")
    public String email;

    public int contactId;
}
