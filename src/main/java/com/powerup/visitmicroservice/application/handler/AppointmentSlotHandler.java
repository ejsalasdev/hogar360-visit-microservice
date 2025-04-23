package com.powerup.visitmicroservice.application.handler;

import com.powerup.visitmicroservice.application.dto.request.SaveAppointmentSlotRequest;
import com.powerup.visitmicroservice.application.dto.response.AppointmentSlotResponse;
import com.powerup.visitmicroservice.application.dto.response.SaveAppointmentSlotResponse;
import com.powerup.visitmicroservice.domain.utils.pagination.PageInfo;

import java.time.LocalDateTime;

public interface AppointmentSlotHandler {
    
    SaveAppointmentSlotResponse save(SaveAppointmentSlotRequest request);
    
    PageInfo<AppointmentSlotResponse> getAllAppointmentSlots(
            Integer page,
            Integer size,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String city
    );
}
