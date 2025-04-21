package com.powerup.visitmicroservice.domain.utils.constants;

public final class AppointmentSlotExceptionMessagesConstants {
    
    public static final String TIME_SLOT_ERROR = "El horario de visita se solapa con un horario existente.";

    private AppointmentSlotExceptionMessagesConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
