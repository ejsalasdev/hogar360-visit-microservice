package com.powerup.visitmicroservice.application.mappers;

import com.powerup.visitmicroservice.application.dto.request.SaveAppointmentSlotRequest;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentSlotRequestMapper {

    AppointmentSlotModel requestToModel(SaveAppointmentSlotRequest request);
}
