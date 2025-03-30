package app.travel.config.middleware.filter;

import app.travel.advice.exception.handler.GlobalExceptionHandler;
import app.travel.common.constant.Error;
import app.travel.shared.payload.response.ResultApiResponse;
import io.swagger.v3.core.util.Json;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MiddlewareFilterConfig extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        try{
            filterChain.doFilter(request, response);
        }
        catch (Exception err){

            Error error = Error.UNAUTHORIZED;

            response.setStatus(error.getStatus().value());
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(
                    Json.pretty(
                            ResultApiResponse.ErrorResponse.builder()
                                    .success(Boolean.FALSE)
                                    .message(err.getMessage())
                                    .status(error.getStatus())
                                    .code(error.getStatus().value())
                                    .path(request.getRequestURI())
                                    .build()
                    )
            );
        }

    }
}
