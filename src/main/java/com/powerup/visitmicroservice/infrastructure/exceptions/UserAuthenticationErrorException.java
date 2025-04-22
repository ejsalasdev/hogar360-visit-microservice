package com.powerup.visitmicroservice.infrastructure.exceptions;

public class UserAuthenticationErrorException extends RuntimeException {
    public UserAuthenticationErrorException(String message) {
        super(message);
    }
}
