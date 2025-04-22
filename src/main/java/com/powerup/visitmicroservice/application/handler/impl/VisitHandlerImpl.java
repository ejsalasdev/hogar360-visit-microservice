package com.powerup.visitmicroservice.application.handler.impl;

import com.powerup.visitmicroservice.application.dto.request.SaveVisitRequest;
import com.powerup.visitmicroservice.application.dto.response.SaveVisitResponse;
import com.powerup.visitmicroservice.application.handler.VisitHandler;
import com.powerup.visitmicroservice.application.mappers.VisitRequestMapper;
import com.powerup.visitmicroservice.application.utils.constants.ApplicationConstants;
import com.powerup.visitmicroservice.domain.ports.in.VisitServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VisitHandlerImpl implements VisitHandler {
    
    private final VisitRequestMapper visitRequestMapper;
    private final VisitServicePort visitServicePort;
    
    @Override
    public SaveVisitResponse save(SaveVisitRequest request) {
        visitServicePort.save(visitRequestMapper.requestToModel(request));
        return new SaveVisitResponse(ApplicationConstants.SAVE_VISIT_RESPONSE, LocalDateTime.now());
    }
}
