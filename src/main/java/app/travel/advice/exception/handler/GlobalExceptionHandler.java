package app.travel.advice.exception.handler;

import app.travel.advice.exception.templates.BadRequestException;
import app.travel.advice.exception.templates.ErrorHolderException;
import app.travel.common.constant.Error;
import app.travel.shared.payload.response.FieldErrorResponse;
import app.travel.shared.payload.response.ResultApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private void logError(Error error){
        log.error("{} - error code {}", error.getMessage(), error.getStatus().value());
    }

    private void writeBodyExceptionResponse(HttpServletRequest request, HttpServletResponse response, Error error) throws IOException {

        response.setStatus(error.getStatus().value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(
                Json.pretty(
                        ResultApiResponse.ErrorResponse.builder()
                                .success(Boolean.FALSE)
                                .message(error.getMessage())
                                .status(error.getStatus())
                                .code(error.getStatus().value())
                                .path(request.getRequestURI())
                                .build()
                )
        );
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        Error error = Error.UNAUTHENTICATED;

        writeBodyExceptionResponse(request, response, error);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        Error error = Error.FORBIDDEN;

        writeBodyExceptionResponse(request, response, error);
    }

    @ExceptionHandler({Exception.class})
    public ResultApiResponse.ErrorResponse handlerServerException(Exception ex){

        System.out.println("-->>  handlerServerException");

        Error error = Error.SERVER_ERROR;

        log.error("Server error {}", ex.getMessage());

        return ResultApiResponse.ErrorResponse.build(error);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultApiResponse.ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException err){

        log.error("Field Not Valid or Method Argument Not Valid Exception.");

        BindingResult bindingResult = err.getBindingResult();

        List<FieldErrorResponse> fieldErrors = bindingResult.getFieldErrors().stream().map(
            fieldError -> FieldErrorResponse.builder()
                    .fieldName(fieldError.getField())
                    .suggestion(fieldError.getDefaultMessage())
                    .build()
        ).toList();

        return ResultApiResponse.ErrorResponse.builder()
                .message("Invalid Fields.")
                .status(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .error(fieldErrors)
                .build();

    }

    @ExceptionHandler({
            BadRequestException.class,
            ErrorHolderException.class
    })
    public ResultApiResponse.ErrorResponse handleCustomException(ErrorHolderException err){

        Error error = err.getError();

        logError(error);

        return ResultApiResponse.ErrorResponse.build(error);
    }

    @ExceptionHandler({DisabledException.class})
    public ResultApiResponse.ErrorResponse handleDisabledException(AuthenticationException err){

        Error error = Error.ACCOUNT_DISABLED;

        logError(error);

        return ResultApiResponse.ErrorResponse.build(error);
    }

    @ExceptionHandler({
            UsernameNotFoundException.class,
            BadCredentialsException.class
    })
    public ResultApiResponse.ErrorResponse handleBadCredentialsException(AuthenticationException err){

        Error error = Error.BAD_CREDENTIALS;

        logError(error);

        return ResultApiResponse.ErrorResponse.build(error);
    }

}

