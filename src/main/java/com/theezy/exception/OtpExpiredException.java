package com.theezy.exception;

public class OtpExpiredException extends RuntimeException{

    public  OtpExpiredException(String message){
        super(message);
    }
}
