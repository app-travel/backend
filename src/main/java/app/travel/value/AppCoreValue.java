package app.travel.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Bag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppCoreValue {

    @Value("${server.servlet.context-path}")
    String prefixPath;

    @Value("${app.path.backend.refresh-token}")
    String refreshTokenPath;

    @Value("${app.path.frontend.signup-confirm}")
    String feSignupConfirmPath;

    @Value("${app.domain.backend}")
    String backendDomain;

    @Value("${app.domain.frontend}")
    String frontendDomain;

    @Value("${app.server.secret-key.crypto.aes-gcm}")
    String serverSecretKey;

    @Value("${app.mail.from}")
    String sendEmailFrom;

}
