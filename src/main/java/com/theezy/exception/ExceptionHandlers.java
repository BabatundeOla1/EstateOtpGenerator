package com.theezy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e){
        return e.getMessage();
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
//        return e.getMessage();
    }

    @ExceptionHandler(VisitorNotFoundException.class)
    public String handleVisitorNotFoundException(VisitorNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(OtpExpiredException.class)
    public String handleOtpExpiredException(OtpExpiredException e){
        return e.getMessage();
    }
}
