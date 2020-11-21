package digital.paisley.phonebook.mapper;

import digital.paisley.phonebook.dto.PhoneDto;
import digital.paisley.phonebook.entities.Phone;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhoneMapper {

    public PhoneDto mapPhoneToDto(Phone phone) {
        return new PhoneDto(phone.getId(), phone.getCountryCode(), phone.getPhoneNumber(), phone.getContact().getId());
    }

    public List<PhoneDto> mapListPhoneToDto(List<Phone> phones) {
        return phones.stream().map(this::mapPhoneToDto).collect(Collectors.toList());
    }
}
