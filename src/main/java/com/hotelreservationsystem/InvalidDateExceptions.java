package com.hotelreservationsystem;

public class InvalidDateExceptions extends Exception{
    enum ExceptionType{
        INVALID_DATE,
        INVALID_PATTERN;
    }
    ExceptionType type;
    public InvalidDateExceptions(ExceptionType type, String message){
        super(type.toString() + ": " + message);
        this.type = type;
    }
}
