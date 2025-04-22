package com.powerup.visitmicroservice.application.exceptions;

public class InvalidUserAccesException extends RuntimeException {
    public InvalidUserAccesException(String message) {
        super(message);
    }
}
