package com.powerup.visitmicroservice.application.handler.impl;

import com.powerup.visitmicroservice.application.dto.request.SaveAppointmentSlotRequest;
import com.powerup.visitmicroservice.application.dto.response.SaveAppointmentSlotResponse;
import com.powerup.visitmicroservice.application.handler.AppointmentSlotHandler;
import com.powerup.visitmicroservice.application.mappers.AppointmentSlotRequestMapper;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.ports.in.AppointmentSlotServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentSlotHandlerImpl implements AppointmentSlotHandler {
    
    private final AppointmentSlotRequestMapper appointmentSlotRequestMapper;
    private final AppointmentSlotServicePort appointmentSlotServicePort;
    
    @Override
    public SaveAppointmentSlotResponse save(SaveAppointmentSlotRequest request) {
        AppointmentSlotModel appointmentSlotModel = appointmentSlotRequestMapper.requestToModel(request);
        appointmentSlotServicePort.save(appointmentSlotModel);
        return new SaveAppointmentSlotResponse("AppointmentSlot created successfully", LocalDateTime.now());
    }
}
