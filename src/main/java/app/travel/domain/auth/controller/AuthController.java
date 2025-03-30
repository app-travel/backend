package app.travel.domain.auth.controller;


import app.travel.common.annotation.DefaultMessage;
import app.travel.domain.auth.payload.request.SignInRequest;
import app.travel.domain.auth.payload.response.SignInResponse;
import app.travel.domain.auth.service.IAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth-Api-Endpoints")
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController implements IAuthController{

    IAuthService authService;

    @Override
    public SignInResponse signIn(@Valid @RequestBody SignInRequest request) {

        System.out.println("-->> signIn");

        return authService.signIn(request);
    }
}
