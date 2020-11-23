package digital.paisley.phonebook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import digital.paisley.phonebook.TestSupport;
import digital.paisley.phonebook.dto.ContactDto;
import digital.paisley.phonebook.services.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ApiControllerTest extends TestSupport {

    @Mock
    private ObjectMapper mockObjectMapper;

    @Mock
    private ContactService mockContactService;

    private ContactController apiControllerUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        apiControllerUnderTest = new ContactController(mockObjectMapper, mockContactService);
    }


    @Test
    void testAddContact() {
        // Setup
        when(mockContactService.add(any())).thenReturn(getContact());
        ContactDto contactDto;
        contactDto = getContactDto();
        // Run the test
        final ResponseEntity<?> result = apiControllerUnderTest.addContact(contactDto);

        ContactDto body = (ContactDto) result.getBody();

        // Verify the results
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.address).isEqualTo(contactDto.address);
    }
}
