package com.powerup.visitmicroservice.domain.ports.out;

import com.powerup.visitmicroservice.domain.model.VisitModel;

import java.util.Optional;

public interface VisitPersistencePort {
    
    void save(VisitModel visitModel);
    int countByAppointmentSlotId(Long appointmentSlotId);
    Optional<VisitModel> findByAppointmentSlotIdAndCustomerEmail(Long appointmentSlotId, String customerEmail);
}
