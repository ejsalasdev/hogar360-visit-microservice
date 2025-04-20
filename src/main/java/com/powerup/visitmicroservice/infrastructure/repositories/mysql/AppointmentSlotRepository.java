package com.powerup.visitmicroservice.infrastructure.repositories.mysql;

import com.powerup.visitmicroservice.infrastructure.entities.AppointmentSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlotEntity, Long> {
    
}
