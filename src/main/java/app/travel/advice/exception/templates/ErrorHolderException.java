package app.travel.advice.exception.templates;

import app.travel.common.constant.Error;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ErrorHolderException extends RuntimeException{

    Error error;

}
