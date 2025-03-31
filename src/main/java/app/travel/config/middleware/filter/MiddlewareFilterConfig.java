package app.travel.config.middleware.filter;

import app.travel.advice.exception.handler.GlobalExceptionHandler;
import app.travel.advice.exception.templates.ErrorHolderException;
import app.travel.common.constant.Error;
import app.travel.domain.users.service.IUserService;
import app.travel.model.roles.RoleEntity;
import app.travel.model.users.UserEntity;
import app.travel.shared.payload.response.ResultApiResponse;
import app.travel.shared.service.jwt.IJwtService;
import app.travel.shared.service.roles.IRoleService;
import io.swagger.v3.core.util.Json;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Slf4j(topic = "MIDDLEWARE-FILTER")
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MiddlewareFilterConfig extends OncePerRequestFilter {

    GlobalExceptionHandler globalExceptionHandler;

    IJwtService jwtService;

    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        log.info("Middleware filter into");

        try{

            String bearerToken = request.getHeader("Authorization");

            String startWith = "Bearer";

            if(bearerToken != null && bearerToken.contains(startWith)){

                String token = bearerToken.substring(startWith.length()).trim();

                if(!jwtService.validateToken(token))
                    throw new ErrorHolderException(Error.INVALID_TOKEN);

                String username = jwtService.getUsernameFromToken(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if(!userDetails.isEnabled())
                    throw new ErrorHolderException(Error.ACCOUNT_DISABLED);

                log.info("Username {} into middleware filter", userDetails.getUsername());

                UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

            filterChain.doFilter(request, response);
        }
        catch (Exception err){

            Error error;

            if(err instanceof ErrorHolderException errHolder)
                error = errHolder.getError();
            else if(err instanceof AuthenticationException errAuth)
                error = Error.UNAUTHENTICATED;
            else error = Error.SERVER_ERROR;

            log.error("Middleware filter error {}", error.getMessage());

            globalExceptionHandler.writeBodyExceptionResponse(request, response, error);
        }

    }
}
