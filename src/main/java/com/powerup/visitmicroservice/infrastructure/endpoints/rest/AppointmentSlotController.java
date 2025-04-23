package com.powerup.visitmicroservice.infrastructure.endpoints.rest;

import com.powerup.visitmicroservice.application.dto.request.SaveAppointmentSlotRequest;
import com.powerup.visitmicroservice.application.dto.response.AppointmentSlotResponse;
import com.powerup.visitmicroservice.application.dto.response.SaveAppointmentSlotResponse;
import com.powerup.visitmicroservice.application.handler.AppointmentSlotHandler;
import com.powerup.visitmicroservice.domain.utils.pagination.PageInfo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/appointmentslot")
@RequiredArgsConstructor
@Tag(name = "AppointmentSlot", description = "Operations related to AppointmentSlots")
public class AppointmentSlotController {
    
    private final AppointmentSlotHandler appointmentSlotHandler;
    
    @PostMapping("/create")
    ResponseEntity<SaveAppointmentSlotResponse> save(@RequestBody SaveAppointmentSlotRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentSlotHandler.save(request));
    }
    
    @GetMapping("/read")
    ResponseEntity<PageInfo<AppointmentSlotResponse>> getAllAppointmentSlots(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Number of homes per page") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "") @RequestParam() LocalDateTime startTime,
            @Parameter(description = "") @RequestParam() LocalDateTime endTime,
            @Parameter(description = "") @RequestParam(required = false) String city
    ) {
        PageInfo<AppointmentSlotResponse> appointmentSlotResponsePageInfo = appointmentSlotHandler.getAllAppointmentSlots(
                page,
                size,
                startTime,
                endTime,
                city
        );
        return ResponseEntity.ok(appointmentSlotResponsePageInfo);
    }
}
