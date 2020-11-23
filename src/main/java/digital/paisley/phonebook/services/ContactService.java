package digital.paisley.phonebook.services;

import digital.paisley.phonebook.clients.GithubClient;
import digital.paisley.phonebook.entities.Contact;
import digital.paisley.phonebook.repositories.ContactRepository;
import digital.paisley.phonebook.repositories.ContactSpecificationsBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContactService {

    final ContactRepository contactRepository;

    private final GithubClient githubClient;

    public ContactService(ContactRepository contactRepository, GithubClient githubClient) {
        this.contactRepository = contactRepository;
        this.githubClient = githubClient;
    }

    public Contact add(Contact contact) {
        contact.setRepos(githubClient.getRepos(contact.getGithubUserName()));
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
