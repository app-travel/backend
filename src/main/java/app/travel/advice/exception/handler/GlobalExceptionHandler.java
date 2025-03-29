package app.travel.advice.exception.handler;

import app.travel.advice.exception.templates.BadRequestException;
import app.travel.advice.exception.templates.ErrorHolderException;
import app.travel.common.constant.Error;
import app.travel.shared.payload.response.FieldErrorResponse;
import app.travel.shared.payload.response.ResultApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private void logError(Error error){
        log.error("{} - error code {}", error.getMessage(), error.getStatus().value());
    }

    @ExceptionHandler({Exception.class})
    public ResultApiResponse.ErrorResponse handlerServerException(Exception ex){

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

    @ExceptionHandler({BadCredentialsException.class})
    public ResultApiResponse.ErrorResponse handleBadCredentialsException(BadCredentialsException err){

        Error error = Error.BAD_CREDENTIALS;

        logError(error);

        return ResultApiResponse.ErrorResponse.build(error);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResultApiResponse.ErrorResponse handleAuthenticationException(AuthenticationException err){

        Error error = Error.UNAUTHENTICATED;

        logError(error);

        return ResultApiResponse.ErrorResponse.build(error);
    }

}

