package app.travel.domain.auth.payload.request;

import app.travel.common.annotation.PasswordFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignInRequest {

    @NotNull(message = "Username is required!")
    @NotBlank(message = "Username is required!")
    String username;

    @PasswordFormat
    String password;

}
