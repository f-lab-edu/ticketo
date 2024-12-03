package org.flab.api.global.exception;

import lombok.Getter;

@Getter
public class NullDataException extends RuntimeException {

    private final ErrorCode errorCode;

    public NullDataException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
