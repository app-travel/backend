package app.travel.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OpenApiValue {

    @Value("${app.openapi.server.url}")
    String serverUrl;

    @Value("${app.openapi.server.description}")
    String serverDescription;

    @Value("${app.openapi.contact.email}")
    String contactEmail;

    @Value("${app.openapi.contact.name}")
    String contactName;

    @Value("${app.openapi.contact.url}")
    String contactUrl;

    @Value("${app.openapi.license.name}")
    String licenseName;

    @Value("${app.openapi.license.url}")
    String licenseUrl;

    @Value("${app.openapi.info.title}")
    String infoTitle;

    @Value("${app.openapi.info.version}")
    String infoVersion;

    @Value("${app.openapi.info.description}")
    String infoDescription;
}
