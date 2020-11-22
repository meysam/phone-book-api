package digital.paisley.phonebook.dto;

import digital.paisley.phonebook.validation.annotation.ValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidPhoneNumber()
public class ContactDto {

    public int id;

    public String firstName;

    public String lastName;

    public String description;

    public String organization;

    public String githubUserName;

    @Email(message = "Please, check entered email is correct",
            regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,10}$")
    public String email;

    public String phoneNumber;

    public String address;

}
