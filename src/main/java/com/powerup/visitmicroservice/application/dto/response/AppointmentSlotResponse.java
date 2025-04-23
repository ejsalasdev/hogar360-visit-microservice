package com.powerup.visitmicroservice.application.dto.response;

import java.time.LocalDateTime;

public record AppointmentSlotResponse(
        Long id,
        Long sellerId,
        Long houseId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
