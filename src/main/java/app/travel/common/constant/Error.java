package app.travel.common.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Error {

    RESOURCE_NOT_FOUND("Resource not found.", HttpStatus.NOT_FOUND),
    BAD_CREDENTIALS("Wrong username or password.", HttpStatus.UNAUTHORIZED),
    UNAUTHENTICATED("You must be logged in.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("You are not authorized to access this resource.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("You don't have permission to access this resource.", HttpStatus.FORBIDDEN),
    SERVER_ERROR("Server error.", HttpStatus.INTERNAL_SERVER_ERROR),
    CLIENT_ERROR("Client error.", HttpStatus.BAD_REQUEST),
    ACCOUNT_DISABLED("Account locked.", HttpStatus.FORBIDDEN),
    INVALID_JWT_SIGNATURE("Invalid token signature.", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED("Token expired.", HttpStatus.UNAUTHORIZED),
    JWT_UNSUPPORTED("JWT unsupported.", HttpStatus.UNAUTHORIZED),
    JWT_PAYLOAD_EMPTY("JWT Payload is empty.", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("Invalid token. Please refresh or login again.", HttpStatus.UNAUTHORIZED),
    COOKIE_NOT_EXISTED("Cookie not existed.", HttpStatus.BAD_REQUEST),;

    // >> ---------------------------------------------------------------------- <<

    String message;
    HttpStatus status;

    Error(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
