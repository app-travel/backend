package app.travel.domain.auth.service;

import app.travel.domain.auth.payload.request.SignInRequest;
import app.travel.domain.auth.payload.response.SignInResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthService {

    SignInResponse signIn(SignInRequest request, HttpServletResponse response);

    SignInResponse refreshToken(HttpServletRequest request);

}
