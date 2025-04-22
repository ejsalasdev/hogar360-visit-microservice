package com.powerup.visitmicroservice.domain.exceptions;

public class DuplicateVisitException extends RuntimeException {
    public DuplicateVisitException(String message) {
        super(message);
    }
}
