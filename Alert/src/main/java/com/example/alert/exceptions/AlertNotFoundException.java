package com.example.alert.exceptions;

public class AlertNotFoundException extends RuntimeException{
    public AlertNotFoundException(String message){
        super(message);
    }
}
