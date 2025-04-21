package com.powerup.visitmicroservice.domain.usecases;

import com.powerup.visitmicroservice.domain.exceptions.TimeSlotConflictException;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.ports.in.AppointmentSlotServicePort;
import com.powerup.visitmicroservice.domain.ports.out.AppointmentSlotPersistencePort;
import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;
import com.powerup.visitmicroservice.domain.utils.validation.TimeSlotValidator;

import java.util.List;
import java.util.Optional;

public class AppointmentSlotUseCase implements AppointmentSlotServicePort {
    
    private final AppointmentSlotPersistencePort appointmentSlotPersistencePort;
    private final AuthenticatedUserPort authenticatedUserPort;
    private final TimeSlotValidator timeSlotValidator;

    public AppointmentSlotUseCase(AppointmentSlotPersistencePort appointmentSlotPersistencePort, AuthenticatedUserPort authenticatedUserPort, TimeSlotValidator timeSlotValidator) {
        this.appointmentSlotPersistencePort = appointmentSlotPersistencePort;
        this.authenticatedUserPort = authenticatedUserPort;
        this.timeSlotValidator = timeSlotValidator;
    }

    @Override
    public void save(AppointmentSlotModel appointmentSlotModel) {
        appointmentSlotModel.setSellerId(authenticatedUserPort.getCurrentUserId());

        Optional<AppointmentSlotModel> existingSameTimeSlot = appointmentSlotPersistencePort.getAllBySellerIdAndStarTime(
                appointmentSlotModel.getSellerId(), appointmentSlotModel.getStartTime()
        );

        if (existingSameTimeSlot.isPresent()) {
            throw new TimeSlotConflictException("Ya existe un horario de visita para esta fecha y hora.");
        }
        
        List<AppointmentSlotModel> existingSlots = appointmentSlotPersistencePort.getAllByHouseId(appointmentSlotModel.getHouseId());

        timeSlotValidator.validate(appointmentSlotModel, existingSlots);
        
        appointmentSlotPersistencePort.save(appointmentSlotModel);
    }
}
