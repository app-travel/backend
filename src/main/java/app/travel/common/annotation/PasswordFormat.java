package app.travel.common.annotation;


import app.travel.common.validator.PasswordFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordFormatValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordFormat {

    String message() default "The password must be at least 8 characters and at most 32 characters long, contain at least one digit, and must contain 1 or 2 special characters like !@#$%^&*.";

    String regex() default "^(?=.*\\d)(?=.*[@.!#$%^&*])[A-Za-z\\d@.!#$%^&*]{8,32}$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
