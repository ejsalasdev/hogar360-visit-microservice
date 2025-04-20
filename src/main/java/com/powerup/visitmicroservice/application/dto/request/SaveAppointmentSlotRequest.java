package com.powerup.visitmicroservice.application.dto.request;

import java.time.LocalDateTime;

public record SaveAppointmentSlotRequest(

        LocalDateTime startTime,
        LocalDateTime endTime,
        Long sellerId,
        Long houseId
) {
}
