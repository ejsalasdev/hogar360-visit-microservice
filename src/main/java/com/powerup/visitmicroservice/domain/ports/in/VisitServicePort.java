package com.powerup.visitmicroservice.domain.ports.in;

import com.powerup.visitmicroservice.domain.model.VisitModel;

public interface VisitServicePort {
    
    void save(VisitModel visitModel);
}
