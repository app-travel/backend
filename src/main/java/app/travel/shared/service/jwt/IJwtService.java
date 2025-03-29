package app.travel.shared.service.jwt;

import app.travel.common.constant.JwtTokenType;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface IJwtService {

    Claims getAllClaimsFromToken(String token);

    String getUsernameFromToken(String token);

    Date getExpirationDateFromToken(String token);

    Boolean isTokenExpired(String token);

    Boolean validateToken(String token);

    String generateToken(UserDetails userDetails, JwtTokenType jwtTokenType);
}
