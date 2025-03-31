package app.travel.shared.service.jwt;

import app.travel.advice.exception.templates.ErrorHolderException;
import app.travel.common.constant.Error;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j(topic = "JWT-SERVICE")
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
            log.error(e.getMessage());
            throw new ErrorHolderException(Error.INVALID_JWT_SIGNATURE);
        } catch (MalformedJwtException e) {
            log.error(e.getMessage());
            throw new ErrorHolderException(Error.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            throw new ErrorHolderException(Error.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error(e.getMessage());
            throw new ErrorHolderException(Error.JWT_UNSUPPORTED);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            throw new ErrorHolderException(Error.JWT_PAYLOAD_EMPTY);
        }
    }

    @Override
    public String generateToken(UserDetails userDetails, JwtTokenType jwtTokenType) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("scp", buildScope(userDetails.getAuthorities()));
        claims.put("aud", Set.of(jwtValue.getIssuer()));

        return createToken(claims, userDetails.getUsername(), jwtTokenType);
    }

    private String buildScope(Collection<? extends GrantedAuthority> grantedAuthorities){

        GrantedAuthority authority = grantedAuthorities.iterator().next();

        Role role = Role.fromAuthority(authority);

        return role.getPrettyRoleName();
    }

    private String createToken(Map<String, Object> claims, String username, JwtTokenType jwtTokenType) {

        Long durationTime = jwtValue.getAccessDurationTime();

        if(jwtTokenType.equals(JwtTokenType.REFRESH))
            durationTime = jwtValue.getRefreshDurationTime();

        return Jwts.builder()
                .header().add("typ", "JWT")
                .and()
                .claims(claims)
                .issuer(jwtValue.getIssuer())
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + durationTime))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }
}
