package com.powerup.visitmicroservice.application.mappers;

import com.powerup.visitmicroservice.application.dto.response.AppointmentSlotResponse;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentSlotResponseMapper {
    
    default AppointmentSlotResponse modelToResponse(AppointmentSlotModel appointmentSlotModel) {
        return new AppointmentSlotResponse(
                appointmentSlotModel.getId(),
                appointmentSlotModel.getSellerId(),
                appointmentSlotModel.getHouseId(),
                appointmentSlotModel.getStartTime(),
                appointmentSlotModel.getEndTime()
        );
    }
}
