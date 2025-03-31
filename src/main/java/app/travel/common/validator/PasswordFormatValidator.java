package app.travel.common.validator;

import app.travel.common.annotation.PasswordFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import java.util.regex.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordFormatValidator implements ConstraintValidator<PasswordFormat, String> {

    String regexPattern;

    @Override
    public void initialize(PasswordFormat constraintAnnotation) {
        this.regexPattern = constraintAnnotation.regex();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        if(password == null) return false;

        return Pattern.matches(regexPattern, password);
    }

}
