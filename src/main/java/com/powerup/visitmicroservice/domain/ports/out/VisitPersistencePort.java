package com.powerup.visitmicroservice.domain.ports.out;

import com.powerup.visitmicroservice.domain.model.VisitModel;

public interface VisitPersistencePort {
    
    void save(VisitModel visitModel);
}
