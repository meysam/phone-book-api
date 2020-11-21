package digital.paisley.phonebook.validation;

import digital.paisley.phonebook.dto.PhoneDto;
import digital.paisley.phonebook.validation.annotation.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, PhoneDto> {

    int minValue;
    int maxValue;

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        minValue = constraintAnnotation.minValue();
        maxValue = constraintAnnotation.maxValue();
    }

    @Override
    public boolean isValid(PhoneDto phoneDto, ConstraintValidatorContext constraintValidatorContext) {

        String number = String.valueOf(phoneDto.phoneNumber);
        int numberLength = number.length();
        return numberLength != 0 && numberLength < maxValue && numberLength > minValue;
    }
}
