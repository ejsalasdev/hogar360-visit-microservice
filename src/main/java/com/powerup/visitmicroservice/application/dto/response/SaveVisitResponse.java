package com.powerup.visitmicroservice.application.dto.response;

import java.time.LocalDateTime;

public record SaveVisitResponse(
        
        String message,
        LocalDateTime timeStamp
) {
}
