package digital.paisley.phonebook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import digital.paisley.phonebook.apis.ContactApi;
import digital.paisley.phonebook.clients.GithubClient;
import digital.paisley.phonebook.dto.ContactDto;
import digital.paisley.phonebook.entities.Contact;
import digital.paisley.phonebook.mapper.ContactMapper;
import digital.paisley.phonebook.services.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ContactController implements ContactApi {

	Logger log = LoggerFactory.getLogger(ContactController.class);

	private final ContactService contactService;

	private final ObjectMapper objectMapper;


	public ContactController(ObjectMapper objectMapper, ContactService contactService) {
		this.objectMapper = objectMapper;
		this.contactService = contactService;
	}

	@Override
	public Optional<ObjectMapper> getObjectMapper() {
		return Optional.ofNullable(objectMapper);
	}

	@Override
	public ResponseEntity<ContactDto> addContact(@Valid ContactDto contactDto) {
		ContactMapper mapper = new ContactMapper();
		Contact contact = mapper.mapDtoToContact(contactDto);
		Contact savedContact = contactService.add(contact);
		return ResponseEntity.ok(mapper.mapContactToDto(savedContact));
	}

	@Override
	public ResponseEntity<?> getContacts(String search) {
		return ResponseEntity.ok(contactService.search(search));
	}
}