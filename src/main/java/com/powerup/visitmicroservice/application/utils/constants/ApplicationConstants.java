package com.powerup.visitmicroservice.application.utils.constants;

public final class ApplicationConstants {
    
    public static final String SAVE_APPOINTMENT_SLOT_RESPONSE = "Appointment slot created successfully";
    public static final String GET_HOUSE_FOR_CURRENT_USER_ERROR = "The requested house does not belong to the current seller";
    public static final String SAVE_VISIT_RESPONSE = "Visit created successfully";

    private ApplicationConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
