package com.powerup.visitmicroservice.domain.ports.out;

import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.utils.pagination.PageInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentSlotPersistencePort {
    
    void save(AppointmentSlotModel appointmentSlotModel);
    List<AppointmentSlotModel> getAllByHouseId(Long houseId);
    Optional<AppointmentSlotModel> getAllBySellerIdAndStarTime(Long sellerId, LocalDateTime startTime);

    PageInfo<AppointmentSlotModel> getdAllAvailable(
            Integer page,
            Integer size,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String city
    );
}
