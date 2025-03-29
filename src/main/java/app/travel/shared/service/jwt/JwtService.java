package app.travel.shared.service.jwt;

import app.travel.common.constant.JwtTokenType;
import app.travel.common.constant.Role;
import app.travel.value.JwtValue;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtService implements IJwtService{

    JwtValue jwtValue;

    @NonFinal
    SecretKey secretKey;

    @PostConstruct
    private void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtValue.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    @Override
    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    @Override
    public Boolean validateToken(String token) {
        try {
            Claims claims = getAllClaimsFromToken(token);
            return claims.getSubject() != null && !isTokenExpired(token);
        } catch (SecurityException e) {
            System.err.println(e.getMessage());
            throw new AuthenticationServiceException("Invalid jwt signature.");
        } catch (MalformedJwtException e) {
            System.err.println(e.getMessage());
            throw new AuthenticationServiceException("Invalid jwt.");
        } catch (ExpiredJwtException e) {
            System.err.println(e.getMessage());
            throw new AuthenticationServiceException("Jwt is expired.");
        } catch (UnsupportedJwtException e) {
            System.err.println(e.getMessage());
            throw new AuthenticationServiceException("Jwt is unsupported.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            throw new AuthenticationServiceException("Jwt payload is empty.");
        }
    }

    @Override
    public String generateToken(UserDetails userDetails, JwtTokenType jwtTokenType) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("scp", buildScope(userDetails.getAuthorities()));
        claims.put("aud", Set.of("travel-app"));

        return createToken(claims, userDetails.getUsername(), jwtTokenType);
    }

    private String buildScope(Collection<? extends GrantedAuthority> grantedAuthorities){

        GrantedAuthority authority = grantedAuthorities.iterator().next();

        Role role = Role.fromAuthority(authority);

        return role.getRoleName();
    }

    private String createToken(Map<String, Object> claims, String username, JwtTokenType jwtTokenType) {

        Long durationTime = jwtValue.getAccessDurationTime();

        if(jwtTokenType.equals(JwtTokenType.REFRESH))
            durationTime = jwtValue.getRefreshDurationTime();

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + durationTime))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }
}
