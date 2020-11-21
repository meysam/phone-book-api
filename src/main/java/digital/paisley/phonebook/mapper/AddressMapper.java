package digital.paisley.phonebook.mapper;

import digital.paisley.phonebook.dto.AddressDto;
import digital.paisley.phonebook.entities.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AddressMapper {

    public AddressDto mapAddressToDto(Address address) {
        return new AddressDto(
                address.getId(),
                address.getZip(),
                address.getCountry(),
                address.getCity(),
                address.getStreet(),
                address.getContact().getId());
    }

    public List<AddressDto> mapListAddressToDto(List<Address> addresses) {
        return addresses.stream().map(this::mapAddressToDto).collect(Collectors.toList());
    }
}
