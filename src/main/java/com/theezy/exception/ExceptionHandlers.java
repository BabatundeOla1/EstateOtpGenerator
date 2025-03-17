package com.theezy.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e){
        return e.getMessage();
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public String handleUserAlreadyExistException(UserAlreadyExistException e){
        return e.getMessage();
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
