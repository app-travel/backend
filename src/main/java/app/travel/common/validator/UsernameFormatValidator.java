package app.travel.common.validator;

import app.travel.common.annotation.UsernameFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import java.util.regex.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsernameFormatValidator implements ConstraintValidator<UsernameFormat, String> {

    String regexPattern;

    @Override
    public void initialize(UsernameFormat constraintAnnotation) {
        this.regexPattern = constraintAnnotation.regex();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

        if(username == null || username.length() < 12)
            return true;

        return Pattern.matches(regexPattern, username);
    }
}
