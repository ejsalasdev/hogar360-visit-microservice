package com.powerup.visitmicroservice.domain.utils.validation;

import com.powerup.visitmicroservice.domain.exceptions.TimeSlotConflictException;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.utils.constants.AppointmentSlotExceptionMessagesConstants;

import java.time.LocalDateTime;
import java.util.List;

public class TimeSlotValidator {

    public void validate(
            AppointmentSlotModel appointmentSlotModel,
            List<AppointmentSlotModel> appointmentSlotModelList
    ) {
        validateBookingWindow(appointmentSlotModel.getStartTime());
        isTimeSlotConflicting(appointmentSlotModel, appointmentSlotModelList);
    }

    private void validateBookingWindow(LocalDateTime startTime) {
        if (startTime != null) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime threeWeeksFromNow = now.plusWeeks(3);
            if (startTime.isBefore(now) || startTime.isAfter(threeWeeksFromNow)) {
                throw new TimeSlotConflictException(
                        AppointmentSlotExceptionMessagesConstants.TIME_SLOT_MUST_BE_FUTURE_DATE_FOR_BOOKING
                );
            }
        }
    }

    private void isTimeSlotConflicting(AppointmentSlotModel newSlot, List<AppointmentSlotModel> existingSlots) {
        for (AppointmentSlotModel existingSlot : existingSlots) {
            if (newSlot.getStartTime().isBefore(existingSlot.getEndTime()) && existingSlot.getStartTime().isBefore(newSlot.getEndTime())) {
                throw new TimeSlotConflictException(
                        AppointmentSlotExceptionMessagesConstants.TIME_SLOT_ERROR
                );
            }
        }
    }
}
