package com.powerup.visitmicroservice.domain.exceptions;

public class TimeSlotConflictException extends RuntimeException {
    public TimeSlotConflictException(String message) {
        super(message);
    }
}
