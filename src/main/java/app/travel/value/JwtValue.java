package app.travel.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtValue {

    @Value("${spring.jwt.issuer}")
    String issuer;

    @Value("${spring.jwt.secret-key}")
    String secretKey;

    @Value("${spring.jwt.access-duration-time}")
    Long accessDurationTime;

    @Value("${spring.jwt.refresh-duration-time}")
    Long refreshDurationTime;
}
