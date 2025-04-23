package com.powerup.visitmicroservice.application.handler.impl;

import com.powerup.visitmicroservice.application.client.dto.HouseInfoResponse;
import com.powerup.visitmicroservice.application.client.handler.PropertyHandlerClient;
import com.powerup.visitmicroservice.application.dto.request.SaveAppointmentSlotRequest;
import com.powerup.visitmicroservice.application.dto.response.AppointmentSlotResponse;
import com.powerup.visitmicroservice.application.dto.response.SaveAppointmentSlotResponse;
import com.powerup.visitmicroservice.application.handler.AppointmentSlotHandler;
import com.powerup.visitmicroservice.application.mappers.AppointmentSlotRequestMapper;
import com.powerup.visitmicroservice.application.mappers.AppointmentSlotResponseMapper;
import com.powerup.visitmicroservice.application.utils.constants.ApplicationConstants;
import com.powerup.visitmicroservice.application.exceptions.InvalidUserAccesException;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.ports.in.AppointmentSlotServicePort;
import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;
import com.powerup.visitmicroservice.domain.utils.pagination.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppointmentSlotHandlerImpl implements AppointmentSlotHandler {

    private final AppointmentSlotRequestMapper appointmentSlotRequestMapper;
    private final AppointmentSlotServicePort appointmentSlotServicePort;
    private final PropertyHandlerClient propertyHandlerClient;
    private final AuthenticatedUserPort authenticatedUserPort;
    private final AppointmentSlotResponseMapper appointmentSlotResponseMapper;

    @Override
    public SaveAppointmentSlotResponse save(SaveAppointmentSlotRequest request) {
        HouseInfoResponse house = propertyHandlerClient.getHouseInfoById(request.houseId());
        Long userId = authenticatedUserPort.getCurrentUserId();

        if (Objects.equals(userId, house.sellerId())) {
            AppointmentSlotModel appointmentSlotModel = appointmentSlotRequestMapper.requestToModel(request);
            appointmentSlotServicePort.save(appointmentSlotModel);
            return new SaveAppointmentSlotResponse(ApplicationConstants.SAVE_APPOINTMENT_SLOT_RESPONSE, LocalDateTime.now());
        }
        throw new InvalidUserAccesException(ApplicationConstants.GET_HOUSE_FOR_CURRENT_USER_ERROR);
    }

    @Override
    public PageInfo<AppointmentSlotResponse> getAllAppointmentSlots(
            Integer page,
            Integer size,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String city
    ) {
        PageInfo<AppointmentSlotModel> appointmentSlotModelPageInfo = appointmentSlotServicePort.getdAllAvailable(
                page,
                size,
                startTime,
                endTime,
                city
        );
        List<AppointmentSlotResponse> appointmentSlotResponses = appointmentSlotModelPageInfo.getContent().stream()
                .map(appointmentSlotResponseMapper::modelToResponse)
                .toList();

        return new PageInfo<>(
                appointmentSlotResponses,
                appointmentSlotModelPageInfo.getTotalElements(),
                appointmentSlotModelPageInfo.getTotalPages(),
                appointmentSlotModelPageInfo.getCurrentPage(),
                appointmentSlotModelPageInfo.getPageSize(),
                appointmentSlotModelPageInfo.isHasNext(),
                appointmentSlotModelPageInfo.isHasPrevious()
        );
    }
}
