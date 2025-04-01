package app.travel.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailTemplateValue {

    @Value("${app.mail.templates.signup-confirm}")
    String signupConfirmTemplate;

    @Value("${app.mail.subjects.signup-confirm}")
    String signupConfirmSubject;
}
