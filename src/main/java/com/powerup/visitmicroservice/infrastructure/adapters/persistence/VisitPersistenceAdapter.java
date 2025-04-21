package com.powerup.visitmicroservice.infrastructure.adapters.persistence;

import com.powerup.visitmicroservice.domain.model.VisitModel;
import com.powerup.visitmicroservice.domain.ports.out.VisitPersistencePort;
import com.powerup.visitmicroservice.infrastructure.entities.VisitEntity;
import com.powerup.visitmicroservice.infrastructure.mappers.VisitEntityMapper;
import com.powerup.visitmicroservice.infrastructure.repositories.mysql.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VisitPersistenceAdapter implements VisitPersistencePort {
    
    private final VisitEntityMapper visitEntityMapper;
    private final VisitRepository visitRepository;

    @Override
    public void save(VisitModel visitModel) {
        VisitEntity visitEntity = visitEntityMapper.modelToEntity(visitModel);
        visitRepository.save(visitEntity);
    }
}
