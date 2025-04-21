package com.powerup.visitmicroservice.domain.exceptions;

public class InvalidUserAccesException extends RuntimeException {
    public InvalidUserAccesException(String message) {
        super(message);
    }
}
