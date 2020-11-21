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
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private int countryCode;
    @Setter
    private long phoneNumber;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Contact contact;

    public Phone(Contact contact) {
        this.contact = contact;
    }

    public Phone(int countryCode, long phoneNumber, Contact contact) {
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.contact = contact;
    }
}
