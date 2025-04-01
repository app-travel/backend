package app.travel.common.annotation;

import app.travel.common.validator.UsernameFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsernameFormatValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameFormat {

    String message() default "Username must contain only lowercase letters, special characters (._@), and at least one digit.";

    String regex() default "^(?=.*\\d)[a-zA-Z\\d.@_]{12,}$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
