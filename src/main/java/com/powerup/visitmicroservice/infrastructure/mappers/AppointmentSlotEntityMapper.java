package com.powerup.visitmicroservice.infrastructure.mappers;

import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.infrastructure.entities.AppointmentSlotEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentSlotEntityMapper {
    
    AppointmentSlotEntity modelToEntity(AppointmentSlotModel appointmentSlotModel);
    
    AppointmentSlotModel entityToModel(AppointmentSlotEntity appointmentSlotEntity);
}
