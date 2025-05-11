package com.theezy.exception;

public class ApartmentAlreadyExistException extends RuntimeException{
    public ApartmentAlreadyExistException(String message){
        super(message);
    }
}
