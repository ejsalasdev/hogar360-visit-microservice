package com.powerup.visitmicroservice.infrastructure.repositories.mysql;

import com.powerup.visitmicroservice.infrastructure.entities.AppointmentSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlotEntity, Long>, JpaSpecificationExecutor<AppointmentSlotEntity> {

    List<AppointmentSlotEntity> findAllByHouseId(Long houseId);

    Optional<AppointmentSlotEntity> findAllBySellerIdAndStartTime(Long sellerId, LocalDateTime startTime);
}
