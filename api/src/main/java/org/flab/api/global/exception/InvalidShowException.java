package org.flab.api.global.exception;

import lombok.Getter;

@Getter
public class InvalidShowException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidShowException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
