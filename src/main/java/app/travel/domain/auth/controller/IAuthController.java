package app.travel.domain.auth.controller;

import app.travel.common.annotation.DefaultMessage;
import app.travel.domain.auth.payload.request.SignInRequest;
import app.travel.domain.auth.payload.request.SignUpRequest;
import app.travel.domain.auth.payload.response.SignInResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface IAuthController {

    @PostMapping("/sign-in")
    @DefaultMessage(message = "Authentication successful.")
    SignInResponse signIn(@Valid @RequestBody SignInRequest request, HttpServletResponse response);

    @PostMapping("/sign-up")
    @DefaultMessage(message = "Send email for register confirm successful.")
    void signUp(@Valid @RequestBody SignUpRequest request) throws Exception;

    @GetMapping("/refresh-token")
    @DefaultMessage(message = "Refreshing successful.")
    SignInResponse refreshToken(HttpServletRequest request);

}
