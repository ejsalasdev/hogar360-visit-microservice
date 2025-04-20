package com.powerup.visitmicroservice.application.dto.response;

import java.time.LocalDateTime;

public record SaveAppointmentSlotResponse(
        String message,
        LocalDateTime timeStamp
) {
}
