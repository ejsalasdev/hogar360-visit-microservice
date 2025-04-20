package com.powerup.visitmicroservice.domain.ports.in;

import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;

public interface AppointmentSlotServicePort {
    
    void save(AppointmentSlotModel appointmentSlotModel);
}
