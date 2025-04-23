package com.powerup.visitmicroservice.domain.ports.in;

import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.utils.pagination.PageInfo;

import java.time.LocalDateTime;

public interface AppointmentSlotServicePort {
    
    void save(AppointmentSlotModel appointmentSlotModel);

    PageInfo<AppointmentSlotModel> getdAllAvailable(
            Integer page,
            Integer size,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String city
    );
}
