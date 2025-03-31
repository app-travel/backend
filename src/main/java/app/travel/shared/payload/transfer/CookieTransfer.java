package app.travel.shared.payload.transfer;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CookieTransfer {

    String key;

    String value;

    @Builder.Default
    String path = "/";

    Integer maxAge;

}
