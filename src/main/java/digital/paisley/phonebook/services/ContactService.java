package digital.paisley.phonebook.services;

import digital.paisley.phonebook.entities.Contact;
import digital.paisley.phonebook.repositories.ContactRepository;
import digital.paisley.phonebook.repositories.ContactSpecificationsBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContactService {

    static final String CONTACT_DOES_NOT_EXIST = "Error! This contact doesn't exist";

    ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact add(Contact contact) {
        //User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(UserService.USER_DOES_NOT_EXIST));
        return contactRepository.save(contact);
    }

    public List<Contact> search(String search) {
        ContactSpecificationsBuilder builder = new ContactSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Contact> spec = builder.build();
        return contactRepository.findAll(spec);
    }

}
