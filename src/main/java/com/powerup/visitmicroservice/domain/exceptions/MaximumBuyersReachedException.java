package com.powerup.visitmicroservice.domain.exceptions;

public class MaximumBuyersReachedException extends RuntimeException {
    public MaximumBuyersReachedException(String message) {
        super(message);
    }
}
