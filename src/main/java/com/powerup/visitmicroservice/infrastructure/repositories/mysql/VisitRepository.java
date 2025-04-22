package com.powerup.visitmicroservice.infrastructure.repositories.mysql;

import com.powerup.visitmicroservice.infrastructure.entities.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {

    int countByAppointmentSlotId_Id(Long appointmentSlotIdId);

    @Query("SELECT v FROM VisitEntity v " +
            "WHERE v.appointmentSlotId.id = :appointmentSlotId " +
            "AND v.customerEmail = :customerEmail")
    Optional<VisitEntity> findByAppointmentSlotIdAndCustomerEmailJPQL(
            @Param("appointmentSlotId") Long appointmentSlotId,
            @Param("customerEmail") String customerEmail
    );
}
