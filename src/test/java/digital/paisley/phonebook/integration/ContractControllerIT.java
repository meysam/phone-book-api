package digital.paisley.phonebook.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import digital.paisley.phonebook.Application;
import digital.paisley.phonebook.TestSupport;
import digital.paisley.phonebook.controllers.ContactController;
import digital.paisley.phonebook.dto.ContactDto;
import digital.paisley.phonebook.repositories.ContactRepository;
import digital.paisley.phonebook.services.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {ContactController.class, ContactService.class, ContactRepository.class})
@SpringBootTest(classes = Application.class)
class ContractControllerIT extends TestSupport {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_200_ok_when_add_a_contact() throws Exception {
        ContactDto contactDto = getContactDto();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(contactDto);
        mockMvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }

}
