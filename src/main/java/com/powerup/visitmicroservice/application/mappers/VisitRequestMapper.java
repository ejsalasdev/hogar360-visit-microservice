package com.powerup.visitmicroservice.application.mappers;

import com.powerup.visitmicroservice.application.dto.request.SaveVisitRequest;
import com.powerup.visitmicroservice.domain.model.VisitModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitRequestMapper {
    
    VisitModel requestToModel(SaveVisitRequest request);
}
