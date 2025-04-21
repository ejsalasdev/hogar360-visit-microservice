package com.powerup.visitmicroservice.domain.usecases;

import com.powerup.visitmicroservice.domain.model.VisitModel;
import com.powerup.visitmicroservice.domain.ports.in.VisitServicePort;
import com.powerup.visitmicroservice.domain.ports.out.VisitPersistencePort;

public class VisitUseCase implements VisitServicePort {
    
    private final VisitPersistencePort visitPersistencePort;

    public VisitUseCase(VisitPersistencePort visitPersistencePort) {
        this.visitPersistencePort = visitPersistencePort;
    }

    @Override
    public void save(VisitModel visitModel) {
        visitPersistencePort.save(visitModel);
    }
}
