package com.powerup.visitmicroservice.infrastructure.repositories.mysql;

import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.infrastructure.entities.AppointmentSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlotEntity, Long> {

    List<AppointmentSlotEntity> findAllByHouseId(Long houseId);

    Optional<AppointmentSlotEntity> findAllBySellerIdAndStartTime(Long sellerId, LocalDateTime startTime);
}
