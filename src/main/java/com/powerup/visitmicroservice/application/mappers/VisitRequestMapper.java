package com.powerup.visitmicroservice.application.mappers;

import com.powerup.visitmicroservice.application.dto.request.SaveVisitRequest;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.model.VisitModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitRequestMapper {
    
    @Mapping(target = "appointmentSlotId", source = "AppointmentSlotId")
    VisitModel requestToModel(SaveVisitRequest request);

    default AppointmentSlotModel longToAppointmentSlotModel(Long appointmentSlotId) {
        if (appointmentSlotId == null) {
            return null;
        }
        AppointmentSlotModel appointmentSlotModel = new AppointmentSlotModel();
        appointmentSlotModel.setId(appointmentSlotId);
        return appointmentSlotModel;
    }
}
