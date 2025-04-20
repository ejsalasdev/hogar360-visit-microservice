package com.powerup.visitmicroservice.domain.usecases;

import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.ports.in.AppointmentSlotServicePort;
import com.powerup.visitmicroservice.domain.ports.out.AppointmentSlotPersistencePort;
import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;

public class AppointmentSlotUseCase implements AppointmentSlotServicePort {
    
    private final AppointmentSlotPersistencePort appointmentSlotPersistencePort;
    private final AuthenticatedUserPort authenticatedUserPort;

    public AppointmentSlotUseCase(AppointmentSlotPersistencePort appointmentSlotPersistencePort, AuthenticatedUserPort authenticatedUserPort) {
        this.appointmentSlotPersistencePort = appointmentSlotPersistencePort;
        this.authenticatedUserPort = authenticatedUserPort;
    }

    @Override
    public void save(AppointmentSlotModel appointmentSlotModel) {
        appointmentSlotModel.setSellerId(authenticatedUserPort.getCurrentUserId());
        appointmentSlotPersistencePort.save(appointmentSlotModel);
    }
}
