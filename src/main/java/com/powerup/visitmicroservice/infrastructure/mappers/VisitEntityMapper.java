package com.powerup.visitmicroservice.infrastructure.mappers;

import com.powerup.visitmicroservice.domain.model.VisitModel;
import com.powerup.visitmicroservice.infrastructure.entities.VisitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitEntityMapper {
    
    VisitEntity modelToEntity(VisitModel visitModel);
    VisitModel entityToModel(VisitEntity visitEntity);
}
