package org.flab.api.global.exception;

import lombok.Getter;

@Getter
public class InvalidEventTypeException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidEventTypeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
