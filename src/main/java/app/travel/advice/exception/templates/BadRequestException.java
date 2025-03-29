package app.travel.advice.exception.templates;

import app.travel.common.constant.Error;


public class BadRequestException extends ErrorHolderException {

    public BadRequestException(Error error) {
        super(error);
    }

}
