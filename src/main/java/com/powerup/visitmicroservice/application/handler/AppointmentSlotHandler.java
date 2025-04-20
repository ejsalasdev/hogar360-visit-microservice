package com.powerup.visitmicroservice.application.handler;

import com.powerup.visitmicroservice.application.dto.request.SaveAppointmentSlotRequest;
import com.powerup.visitmicroservice.application.dto.response.SaveAppointmentSlotResponse;

public interface AppointmentSlotHandler {
    
    SaveAppointmentSlotResponse save(SaveAppointmentSlotRequest request);
}
