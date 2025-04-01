package app.travel.shared.service.mailer;

import app.travel.domain.auth.payload.request.SignUpRequest;

public interface IMailerService {

    void sendToEmail(String toEmail, String subjectEmail, String htmlContent);

    void sendEmailConfirmSignup(SignUpRequest request);

}
