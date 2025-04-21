package com.powerup.visitmicroservice.infrastructure.exceptionhandler;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String message,
        LocalDateTime timeStamp
) {
}
