package com.theezy.exception;

public class ApartmentHasBeenOccupied extends RuntimeException{

    public ApartmentHasBeenOccupied (String message){
        super(message);
    }
}
