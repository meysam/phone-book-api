package digital.paisley.phonebook.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
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


    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private final List<Email> emails = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private final List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private final List<Address> addresses = new ArrayList<>();

    public Contact(String firstName) {
        this.firstName = firstName;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    public void addEmail(Email email) {
        emails.add(email);
    }

    public void addPhones(List<Phone> numbers) {
        phones.addAll(numbers);
    }

    public void addAddresses(List<Address> addresses) {
        this.addresses.addAll(addresses);
    }

    public void addEmails(List<Email> emails) {
        this.emails.addAll(emails);
    }

    public List<Phone> getPhoneNumbers() {
        return Collections.unmodifiableList(phones);
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public List<Email> getEmails() {
        return Collections.unmodifiableList(emails);
    }

    public String getOrganization() {
        return organization;
    }

    public String getGithubUserName() {
        return githubUserName;
    }
}
