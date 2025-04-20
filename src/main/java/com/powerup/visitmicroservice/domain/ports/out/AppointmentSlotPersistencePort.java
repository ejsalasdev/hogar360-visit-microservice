package com.powerup.visitmicroservice.domain.ports.out;

import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;

import java.util.Optional;

public interface AppointmentSlotPersistencePort {
    
    void save(AppointmentSlotModel appointmentSlotModel);
}
