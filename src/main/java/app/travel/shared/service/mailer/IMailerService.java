package app.travel.shared.service.mailer;

import app.travel.domain.auth.payload.request.SignUpRequest;
import jakarta.mail.MessagingException;


public interface IMailerService {

    void sendToEmail(String fromEmail, String toEmail, String subjectInEmail, String contentEmail) throws MessagingException;

    void sendEmailConfirmSignup(SignUpRequest request) throws Exception;

}
