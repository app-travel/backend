package app.travel.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppCoreValue {

    @Value("${server.servlet.context-path}")
    String prefixPath;

    @Value("${app.api.path.refresh-token}")
    String refreshTokenPath;

}
