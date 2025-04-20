package com.powerup.visitmicroservice.infrastructure.adapters.persistence;

import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.ports.out.AppointmentSlotPersistencePort;
import com.powerup.visitmicroservice.infrastructure.entities.AppointmentSlotEntity;
import com.powerup.visitmicroservice.infrastructure.mappers.AppointmentSlotEntityMapper;
import com.powerup.visitmicroservice.infrastructure.repositories.mysql.AppointmentSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppointmentSlotPersistenceAdapter implements AppointmentSlotPersistencePort {
    
    private final AppointmentSlotEntityMapper appointmentSlotEntityMapper;
    private final AppointmentSlotRepository appointmentSlotRepository;
    
    @Override
    public void save(AppointmentSlotModel appointmentSlotModel) {
        AppointmentSlotEntity appointmentSlotEntity = appointmentSlotEntityMapper.modelToEntity(appointmentSlotModel);
        appointmentSlotRepository.save(appointmentSlotEntity);
    }
}
