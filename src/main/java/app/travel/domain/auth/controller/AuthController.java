package app.travel.domain.auth.controller;


import app.travel.domain.auth.payload.request.SignInRequest;
import app.travel.domain.auth.payload.response.SignInResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth-Api-Endpoints")
public class AuthController implements IAuthController{



    @Override
    public SignInResponse signIn(SignInRequest request) {
        return null;
    }
}
