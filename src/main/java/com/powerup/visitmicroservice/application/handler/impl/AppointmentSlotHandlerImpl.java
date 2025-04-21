package com.powerup.visitmicroservice.application.handler.impl;

import com.powerup.visitmicroservice.application.client.dto.HouseInfoResponse;
import com.powerup.visitmicroservice.application.client.handler.PropertyHandlerClient;
import com.powerup.visitmicroservice.application.dto.request.SaveAppointmentSlotRequest;
import com.powerup.visitmicroservice.application.dto.response.SaveAppointmentSlotResponse;
import com.powerup.visitmicroservice.application.handler.AppointmentSlotHandler;
import com.powerup.visitmicroservice.application.mappers.AppointmentSlotRequestMapper;
import com.powerup.visitmicroservice.domain.exceptions.InvalidUserAccesException;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.ports.in.AppointmentSlotServicePort;
import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppointmentSlotHandlerImpl implements AppointmentSlotHandler {
    
    private final AppointmentSlotRequestMapper appointmentSlotRequestMapper;
    private final AppointmentSlotServicePort appointmentSlotServicePort;
    private final PropertyHandlerClient propertyHandlerClient;
    private final AuthenticatedUserPort authenticatedUserPort;
    
    @Override
    public SaveAppointmentSlotResponse save(SaveAppointmentSlotRequest request) {
        HouseInfoResponse house = propertyHandlerClient.getHouseInfoById(request.houseId());
        Long userId = authenticatedUserPort.getCurrentUserId();
        
        if (Objects.equals(userId, house.sellerId())){
            AppointmentSlotModel appointmentSlotModel = appointmentSlotRequestMapper.requestToModel(request);
            appointmentSlotServicePort.save(appointmentSlotModel);
            return new SaveAppointmentSlotResponse("AppointmentSlot created successfully", LocalDateTime.now());
        }
        throw new InvalidUserAccesException("La casa solicitada no corresponde con el vendedor actual");
    }
}
