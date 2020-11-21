package digital.paisley.phonebook.mapper;

import digital.paisley.phonebook.dto.ContactDto;
import digital.paisley.phonebook.entities.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    PhoneMapper phoneMapper = new PhoneMapper();
    AddressMapper addressMapper = new AddressMapper();
    EmailMapper emailMapper = new EmailMapper();

    public ContactDto mapContactToDtoFull(Contact contact) {
        return new ContactDto(contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getDescription(),
                contact.getOrganization(),
                contact.getGithubUserName(),
                phoneMapper.mapListPhoneToDto(contact.getPhoneNumbers()),
                addressMapper.mapListAddressToDto(contact.getAddresses()),
                emailMapper.mapListEmailToDto(contact.getEmails()));
    }

    public ContactDto mapContactToDto(Contact contact) {
        return new ContactDto(contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getDescription(),
                contact.getOrganization(),
                contact.getGithubUserName());
    }
}
