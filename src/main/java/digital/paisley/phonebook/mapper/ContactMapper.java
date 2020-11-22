package digital.paisley.phonebook.mapper;

import digital.paisley.phonebook.dto.ContactDto;
import digital.paisley.phonebook.entities.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public ContactDto mapContactToDto(Contact contact) {
        return ContactDto.builder()
                .id(contact.getId())
                .address(contact.getAddress())
                .description(contact.getDescription())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .githubUserName(contact.getGithubUserName())
                .phoneNumber(contact.getPhoneNumber())
                .organization(contact.getOrganization())
                .email(contact.getEmail())
                .build();
    }

    public Contact mapDtoToContact(ContactDto contactDto) {
        return Contact.builder()
                .address(contactDto.address)
                .description(contactDto.description)
                .firstName(contactDto.firstName)
                .lastName(contactDto.lastName)
                .githubUserName(contactDto.githubUserName)
                .phoneNumber(contactDto.phoneNumber)
                .organization(contactDto.organization)
                .email(contactDto.email)
                .build();
    }
}
