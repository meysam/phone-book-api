package digital.paisley.phonebook.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String description;
    @Setter
    private String organization;
    @Setter
    private String githubUserName;
    @Setter
    private String email;
    @Setter
    private String phoneNumber;
    @Setter
    private  String address;
    @Setter
    @ElementCollection
    private List<String> repos;

    public Contact(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getOrganization() {
        return organization;
    }

    public String getGithubUserName() {
        return githubUserName;
    }

    public List<String> getRepos() {
        return repos;
    }
}
