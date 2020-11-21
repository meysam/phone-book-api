package digital.paisley.phonebook.validation.annotation;

import digital.paisley.phonebook.validation.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Documented
public @interface ValidPhoneNumber {

    int minValue();

    int maxValue();

    String message() default "The phone number should be greater than 9 digits and less than 16 digits";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
