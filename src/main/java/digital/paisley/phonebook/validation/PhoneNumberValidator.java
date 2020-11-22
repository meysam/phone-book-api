package digital.paisley.phonebook.validation;

import digital.paisley.phonebook.dto.ContactDto;
import digital.paisley.phonebook.validation.annotation.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, ContactDto> {

    @Override
    public boolean isValid(ContactDto contactDto, ConstraintValidatorContext constraintValidatorContext) {

        Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
        Matcher matcher = pattern.matcher(contactDto.phoneNumber);
        return  matcher.matches();
    }
}
