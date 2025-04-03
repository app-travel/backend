package app.travel.domain.auth.payload.request;

import app.travel.common.annotation.PasswordFormat;
import app.travel.common.annotation.UsernameFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 12, message = "Username must at least 12 characters.")
    @UsernameFormat
    @Schema(defaultValue = "admin.trave1@app")
    String username;

    @PasswordFormat
    @Schema(defaultValue = "admin123@travel.app")
    String password;

}
