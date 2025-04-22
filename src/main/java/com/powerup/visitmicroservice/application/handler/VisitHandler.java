package com.powerup.visitmicroservice.application.handler;

import com.powerup.visitmicroservice.application.dto.request.SaveVisitRequest;
import com.powerup.visitmicroservice.application.dto.response.SaveVisitResponse;

public interface VisitHandler {
    
    SaveVisitResponse save(SaveVisitRequest request);
}
