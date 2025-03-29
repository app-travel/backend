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
    ACCOUNT_DISABLED("Account locked.", HttpStatus.LOCKED);

    // >> ---------------------------------------------------------------------- <<

    String message;
    HttpStatus status;

    Error(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
