package app.travel.domain.auth.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {

    String firstName;

    String lastName;

    String phoneNumber;

    String username;

    String email;

    String password;

    String confirmPassword;

}
