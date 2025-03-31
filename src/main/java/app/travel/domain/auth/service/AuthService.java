package app.travel.domain.auth.service;

import app.travel.advice.exception.templates.ErrorHolderException;
import app.travel.common.constant.Error;
import app.travel.common.constant.JwtTokenType;
import app.travel.domain.auth.payload.request.SignInRequest;
import app.travel.domain.auth.payload.response.SignInResponse;
import app.travel.domain.users.service.IUserService;
import app.travel.model.roles.RoleEntity;
import app.travel.model.tokens.TokenEntity;
import app.travel.model.users.UserEntity;
import app.travel.shared.identity.UserDetailsImpl;
import app.travel.shared.payload.response.JwtTokenResponse;
import app.travel.shared.payload.transfer.CookieTransfer;
import app.travel.shared.service.cookie.ICookieService;
import app.travel.shared.service.jwt.IJwtService;
import app.travel.shared.service.roles.IRoleService;
import app.travel.shared.service.tokens.ITokenService;
import app.travel.value.AppCoreValue;
import app.travel.value.JwtValue;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService implements IAuthService{

    AuthenticationManager authenticationManager;

    AppCoreValue appCoreValue;

    JwtValue jwtValue;

    IJwtService jwtService;

    ITokenService tokenService;

    ICookieService cookieService;

    IUserService userService;

    IRoleService roleService;

    @Override
    @Transactional
    public SignInResponse signIn(SignInRequest request, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword())
        );

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(userDetails, JwtTokenType.ACCESS);

        String refreshToken = jwtService.generateToken(userDetails, JwtTokenType.REFRESH);

        UserEntity userEntity = userDetails.getUser();

        TokenEntity accessTokenEntity = tokenService.saveTokenInclude(accessToken, JwtTokenType.ACCESS, userEntity);

        TokenEntity refreshTokenEntity = tokenService.saveTokenInclude(refreshToken, JwtTokenType.REFRESH, userEntity);

        CookieTransfer cookieTransfer = CookieTransfer.builder()
                .key(JwtTokenType.REFRESH.getNameSpecial())
                .value(refreshTokenEntity.getId().toString())
                .path(appCoreValue.getRefreshTokenPath())
                .maxAge((int) (jwtValue.getRefreshDurationTime() / 1000))
                .build();

        cookieService.sendCookieInclude(cookieTransfer, response);

        JwtTokenResponse tokensResponse = JwtTokenResponse.builder()
                .access(accessTokenEntity.getToken())
//                .refresh(refreshTokenEntity.getId())
                .build();

        return SignInResponse.builder()
                .token(tokensResponse)
                .build();
    }

    @Override
    @Transactional
    public SignInResponse refreshToken(HttpServletRequest request) {

        Cookie cookie = cookieService.getCookieFrom(request, JwtTokenType.REFRESH.getNameSpecial());

        UUID refreshTokenId = UUID.fromString(cookie.getValue());

        TokenEntity tokenEntity = tokenService.getTokenById(refreshTokenId);

        String refreshToken = tokenEntity.getToken();

        Boolean tokenValid = jwtService.validateToken(refreshToken);

        if(!tokenValid){

            tokenService.deleteToken(tokenEntity);

            throw new ErrorHolderException(Error.INVALID_TOKEN);
        }

        UserEntity userEntity = userService.getUserById(tokenEntity.getUserId());

        RoleEntity roleEntity = roleService.getRoleById(userEntity.getRoleId());

        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .user(userEntity)
                .role(roleEntity)
                .build();

        String accessToken = jwtService.generateToken(userDetails, JwtTokenType.ACCESS);

        tokenEntity = tokenService.saveTokenInclude(accessToken, JwtTokenType.ACCESS, userEntity);

        JwtTokenResponse jwtTokenResponse = JwtTokenResponse.builder()
                .access(tokenEntity.getToken())
                .build();

        return SignInResponse.builder()
                .token(jwtTokenResponse)
                .build();
    }
}
