package com.theezy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(VisitorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVisitorNotFoundException(VisitorNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OtpExpiredException.class)
    public ResponseEntity<ErrorResponse> handleOtpExpiredException(OtpExpiredException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
