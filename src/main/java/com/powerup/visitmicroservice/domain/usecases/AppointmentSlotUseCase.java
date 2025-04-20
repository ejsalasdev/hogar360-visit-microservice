package com.powerup.visitmicroservice.domain.usecases;

import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.ports.in.AppointmentSlotServicePort;
import com.powerup.visitmicroservice.domain.ports.out.AppointmentSlotPersistencePort;

public class AppointmentSlotUseCase implements AppointmentSlotServicePort {
    
    private final AppointmentSlotPersistencePort appointmentSlotPersistencePort;

    public AppointmentSlotUseCase(AppointmentSlotPersistencePort appointmentSlotPersistencePort) {
        this.appointmentSlotPersistencePort = appointmentSlotPersistencePort;
    }

    @Override
    public void save(AppointmentSlotModel appointmentSlotModel) {
        appointmentSlotPersistencePort.save(appointmentSlotModel);
    }
}
