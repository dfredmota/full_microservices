package com.developersd3.microservices.exceptions;

public class UserServiceException extends RuntimeException {

    public UserServiceException(String message){
        super(message);
    }
}
