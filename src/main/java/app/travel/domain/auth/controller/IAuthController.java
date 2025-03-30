package app.travel.domain.auth.controller;

import app.travel.common.annotation.DefaultMessage;
import app.travel.domain.auth.payload.request.SignInRequest;
import app.travel.domain.auth.payload.response.SignInResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface IAuthController {

    @PostMapping("/sign-in")
    @DefaultMessage(message = "Authentication successful.")
    @ResponseStatus(HttpStatus.OK)
    SignInResponse signIn(@Valid @RequestBody SignInRequest request);

}
