package com.powerup.visitmicroservice.infrastructure.endpoints.rest;

import com.powerup.visitmicroservice.application.dto.request.SaveVisitRequest;
import com.powerup.visitmicroservice.application.dto.response.SaveVisitResponse;
import com.powerup.visitmicroservice.application.handler.VisitHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visit")
@RequiredArgsConstructor
@Tag(name = "Visit", description = "Operations related to Visits")
public class VisitController {
    
    private final VisitHandler visitHandler;
    
    @PostMapping("/create")
    ResponseEntity<SaveVisitResponse> save(@RequestBody SaveVisitRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(visitHandler.save(request));
    }
}
