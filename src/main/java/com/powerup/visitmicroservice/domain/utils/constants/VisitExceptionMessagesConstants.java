package com.powerup.visitmicroservice.domain.utils.constants;

public final class VisitExceptionMessagesConstants {

    public static final String VISIT_ALREADY_EXIST_FOR_BUYER = "You have already scheduled a visit for this time.";
    public static final String VISIT_LIMIT_REACHED_ERROR = "All available slots for this time have already been booked.";

    private VisitExceptionMessagesConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
