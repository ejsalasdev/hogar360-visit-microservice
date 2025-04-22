package com.powerup.visitmicroservice.domain.utils.constants;

public final class AppointmentSlotExceptionMessagesConstants {
    
    public static final String TIME_SLOT_ERROR = "The visit time overlaps with an existing schedule.";
    public static final String SAME_APPOINTMENT_SLOT_ERROR = "An appointment slot already exists for this date and time.";
    public static final String TIME_SLOT_MUST_BE_FUTURE_DATE_FOR_BOOKING = "The startTime must be within the next 3 weeks from now.";

    private AppointmentSlotExceptionMessagesConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
