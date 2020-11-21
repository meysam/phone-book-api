package digital.paisley.phonebook.mapper;

import digital.paisley.phonebook.dto.EmailDto;
import digital.paisley.phonebook.entities.Email;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailMapper {

    public EmailDto mapEmailToDto(Email email) {
        return new EmailDto(email.getId(), email.getEmail(), email.getContact().getId());
    }


    public List<EmailDto> mapListEmailToDto(List<Email> emails) {
        return emails.stream().map(this::mapEmailToDto).collect(Collectors.toList());
    }


}
