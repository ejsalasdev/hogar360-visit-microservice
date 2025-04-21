package com.powerup.visitmicroservice.infrastructure.repositories.mysql;

import com.powerup.visitmicroservice.infrastructure.entities.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {
    
    
}
