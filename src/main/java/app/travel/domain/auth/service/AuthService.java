package app.travel.domain.auth.service;

import app.travel.advice.exception.templates.ErrorHolderException;
import app.travel.common.constant.Error;
import app.travel.common.constant.JwtTokenType;
import app.travel.domain.auth.payload.request.SignInRequest;
import app.travel.domain.auth.payload.response.SignInResponse;
import app.travel.shared.payload.response.JwtTokenResponse;
import app.travel.shared.service.jwt.IJwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService implements IAuthService{

    AuthenticationManager authenticationManager;

    IJwtService jwtService;

    @Override
    public SignInResponse signIn(SignInRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if(!userDetails.isEnabled())
            throw new ErrorHolderException(Error.ACCOUNT_DISABLED);

        String accessToken = jwtService.generateToken(userDetails, JwtTokenType.ACCESS);

        String refreshToken = jwtService.generateToken(userDetails, JwtTokenType.REFRESH);

        JwtTokenResponse tokensResponse = JwtTokenResponse.builder()
                .access(accessToken)
                .refresh(refreshToken)
                .build();

        return SignInResponse.builder()
                .tokens(tokensResponse)
                .build();
    }
}
