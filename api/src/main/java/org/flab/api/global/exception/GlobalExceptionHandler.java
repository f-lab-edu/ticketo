package org.flab.api.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = new ErrorResponse(errorCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ErrorResponse response = new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // @RequestBody validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        ErrorResponse response = getErrorResponseByMessage(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // @ModelAttribute validation error
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        String message = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        ErrorResponse response = getErrorResponseByMessage(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public ErrorResponse getErrorResponseByMessage(String message) {
        ErrorCode errorCode = ErrorCode.valueOfOrNull(message);

        if (errorCode == null) {
            return new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, message);
        } else {
            return new ErrorResponse(errorCode);
        }
    }
}