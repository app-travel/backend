package app.travel.domain.auth.service;

import app.travel.domain.auth.payload.request.SignInRequest;
import app.travel.domain.auth.payload.response.SignInResponse;

public interface IAuthService {

    SignInResponse signIn(SignInRequest request);

}
