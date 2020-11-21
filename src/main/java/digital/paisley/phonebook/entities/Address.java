package digital.paisley.phonebook.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String zip;
    @Setter
    private String country;
    @Setter
    private String city;
    @Setter
    private String street;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Contact contact;

    public Address(Contact contact) {
        this.contact = contact;
    }

    public Address(String zip, String country, String city, String street, Contact contact) {
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.contact =contact;
    }
}
