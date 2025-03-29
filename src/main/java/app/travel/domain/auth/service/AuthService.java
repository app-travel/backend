package app.travel.domain.auth.service;

import app.travel.advice.exception.templates.ErrorHolderException;
import app.travel.common.constant.Error;
import app.travel.domain.auth.payload.request.SignInRequest;
import app.travel.domain.auth.payload.response.SignInResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService implements IAuthService{

    AuthenticationManager authenticationManager;


    @Override
    public SignInResponse signIn(SignInRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if(!userDetails.isEnabled())
            throw new ErrorHolderException(Error.ACCOUNT_DISABLED);



        return null;
    }
}
