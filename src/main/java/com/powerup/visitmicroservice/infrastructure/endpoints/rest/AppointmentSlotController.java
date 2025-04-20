package com.powerup.visitmicroservice.infrastructure.endpoints.rest;

import com.powerup.visitmicroservice.application.dto.request.SaveAppointmentSlotRequest;
import com.powerup.visitmicroservice.application.dto.response.SaveAppointmentSlotResponse;
import com.powerup.visitmicroservice.application.handler.AppointmentSlotHandler;
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
@Tag(name = "Visit", description = "Operations related to visits")
public class AppointmentSlotController {
    
    private final AppointmentSlotHandler appointmentSlotHandler;
    
    @PostMapping("/create")
    ResponseEntity<SaveAppointmentSlotResponse> save(@RequestBody SaveAppointmentSlotRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentSlotHandler.save(request));
    }
}
