package app.travel.shared.service.mailer;

import app.travel.domain.auth.payload.request.SignUpRequest;
import app.travel.value.MailTemplateValue;
import io.swagger.v3.core.util.Json;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailerService implements IMailerService{

    JavaMailSender mailSender;

    MailTemplateValue mailTemplateValue;

    SpringTemplateEngine templateEngine;

    @Override
    public void sendEmailConfirmSignup(SignUpRequest request) {

        String signupIn4ToJson = Json.pretty(request);



        Context context = new Context();
        context.setVariable("link-fe-signin-confirm", "");
        context.setVariable("year", LocalDateTime.now().getYear());

        String htmlContent = templateEngine.process(mailTemplateValue.getSignupConfirmTemplate(), context);
        String sendTo = request.getEmail();
        String subject = mailTemplateValue.getSignupConfirmSubject();


        sendToEmail(sendTo, subject, htmlContent);
    }

    @Override
    public void sendToEmail(String toEmail, String subjectEmail, String htmlContent) {



    }
}
