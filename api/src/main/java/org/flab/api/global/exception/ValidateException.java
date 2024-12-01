package org.flab.api.global.exception;

import lombok.Getter;

@Getter
public class ValidateException extends RuntimeException {

    private final ErrorCode errorCode;

    public ValidateException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
