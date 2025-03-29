package app.travel.shared.payload.response;

import app.travel.common.constant.Error;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultApiResponse {

    Boolean success;

    String message;

    HttpStatus status;

    Integer code;

    String path;

    @Builder.ObtainVia
    @Setter(AccessLevel.NONE)
    final OffsetDateTime timestamp = OffsetDateTime.now(ZoneId.systemDefault());

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Object data;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ErrorResponse extends ResultApiResponse {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Object error;

        public static ResultApiResponse.ErrorResponse build(Error err){

            return ErrorResponse.builder()
                    .success(Boolean.FALSE)
                    .message(err.getMessage())
                    .status(err.getStatus())
                    .code(err.getStatus().value())
                    .build();
        }

    }

}
