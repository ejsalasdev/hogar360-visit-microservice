package com.powerup.visitmicroservice.domain.utils.validation;

import com.powerup.visitmicroservice.domain.exceptions.TimeSlotConflictException;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotValidatorTest {

    private final TimeSlotValidator timeSlotValidator = new TimeSlotValidator();

    @Test
    void When_Validate_And_StartTimeIsInBookingWindow_Expect_NoException() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusDays(1);
        LocalDateTime endTime = startTime.plusHours(1);
        AppointmentSlotModel appointmentSlotModel = new AppointmentSlotModel(null, startTime, endTime, 1L, 1L);
        List<AppointmentSlotModel> existingSlots = Collections.emptyList();

        // Act & Assert
        assertDoesNotThrow(() -> timeSlotValidator.validate(appointmentSlotModel, existingSlots));
    }

    @Test
    void When_Validate_And_StartTimeIsBeforeNow_Expect_TimeSlotConflictException() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.minusDays(1);
        LocalDateTime endTime = startTime.plusHours(1);
        AppointmentSlotModel appointmentSlotModel = new AppointmentSlotModel(null, startTime, endTime, 1L, 1L);
        List<AppointmentSlotModel> existingSlots = Collections.emptyList();

        // Act & Assert
        assertThrows(TimeSlotConflictException.class, () -> timeSlotValidator.validate(appointmentSlotModel, existingSlots));
    }

    @Test
    void When_Validate_And_StartTimeIsAfterThreeWeeks_Expect_TimeSlotConflictException() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusWeeks(3).plusDays(1);
        LocalDateTime endTime = startTime.plusHours(1);
        AppointmentSlotModel appointmentSlotModel = new AppointmentSlotModel(null, startTime, endTime, 1L, 1L);
        List<AppointmentSlotModel> existingSlots = Collections.emptyList();

        // Act & Assert
        assertThrows(TimeSlotConflictException.class, () -> timeSlotValidator.validate(appointmentSlotModel, existingSlots));
    }

    @Test
    void When_Validate_And_NoConflictWithExistingSlots_Expect_NoException() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newStartTime = now.plusDays(1);
        LocalDateTime newEndTime = newStartTime.plusHours(1);
        AppointmentSlotModel newSlot = new AppointmentSlotModel(null, newStartTime, newEndTime, 1L, 1L);

        LocalDateTime existingStartTime1 = now.plusDays(2);
        LocalDateTime existingEndTime1 = existingStartTime1.plusHours(1);
        AppointmentSlotModel existingSlot1 = new AppointmentSlotModel(1L, existingStartTime1, existingEndTime1, 2L, 1L);

        LocalDateTime existingStartTime2 = now.plusHours(1);
        LocalDateTime existingEndTime2 = existingStartTime2.plusHours(1);
        AppointmentSlotModel existingSlot2 = new AppointmentSlotModel(2L, existingStartTime2, existingEndTime2, 3L, 1L);

        List<AppointmentSlotModel> existingSlots = Arrays.asList(existingSlot1, existingSlot2);

        // Act & Assert
        assertDoesNotThrow(() -> timeSlotValidator.validate(newSlot, existingSlots));
    }

    @Test
    void When_Validate_And_NewSlotStartTimeIsBeforeExistingEndTime_And_ExistingStartTimeIsBeforeNewSlotEndTime_Expect_TimeSlotConflictException() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newStartTime = now.plusDays(1).plusHours(1);
        LocalDateTime newEndTime = newStartTime.plusHours(1);
        AppointmentSlotModel newSlot = new AppointmentSlotModel(null, newStartTime, newEndTime, 1L, 1L);

        LocalDateTime existingStartTime = now.plusDays(1);
        LocalDateTime existingEndTime = existingStartTime.plusHours(2);
        AppointmentSlotModel existingSlot = new AppointmentSlotModel(1L, existingStartTime, existingEndTime, 2L, 1L);

        List<AppointmentSlotModel> existingSlots = Collections.singletonList(existingSlot);

        // Act & Assert
        assertThrows(TimeSlotConflictException.class, () -> timeSlotValidator.validate(newSlot, existingSlots));
    }

    @Test
    void When_Validate_And_NewSlotStartTimeEqualsExistingStartTime_Expect_TimeSlotConflictException() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newStartTime = now.plusDays(1);
        LocalDateTime newEndTime = newStartTime.plusHours(1);
        AppointmentSlotModel newSlot = new AppointmentSlotModel(null, newStartTime, newEndTime, 1L, 1L);

        LocalDateTime existingStartTime = now.plusDays(1);
        LocalDateTime existingEndTime = existingStartTime.plusHours(2);
        AppointmentSlotModel existingSlot = new AppointmentSlotModel(1L, existingStartTime, existingEndTime, 2L, 1L);

        List<AppointmentSlotModel> existingSlots = Collections.singletonList(existingSlot);

        // Act & Assert
        assertThrows(TimeSlotConflictException.class, () -> timeSlotValidator.validate(newSlot, existingSlots));
    }

    @Test
    void When_Validate_And_NewSlotEndTimeEqualsExistingEndTime_Expect_TimeSlotConflictException() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newStartTime = now.plusDays(1);
        LocalDateTime newEndTime = newStartTime.plusHours(1);
        AppointmentSlotModel newSlot = new AppointmentSlotModel(null, newStartTime, newEndTime, 1L, 1L);

        LocalDateTime existingStartTime = now.plusDays(1).plusMinutes(30);
        AppointmentSlotModel existingSlot = new AppointmentSlotModel(1L, existingStartTime, newEndTime, 2L, 1L);

        List<AppointmentSlotModel> existingSlots = Collections.singletonList(existingSlot);

        // Act & Assert
        assertThrows(TimeSlotConflictException.class, () -> timeSlotValidator.validate(newSlot, existingSlots));
    }

    @Test
    void When_Validate_And_NewSlotCompletelyOverlapsExistingSlot_Expect_TimeSlotConflictException() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newStartTime = now.plusDays(1);
        LocalDateTime newEndTime = newStartTime.plusHours(2);
        AppointmentSlotModel newSlot = new AppointmentSlotModel(null, newStartTime, newEndTime, 1L, 1L);

        LocalDateTime existingStartTime = now.plusDays(1).plusMinutes(30);
        LocalDateTime existingEndTime = newStartTime.plusHours(1);
        AppointmentSlotModel existingSlot = new AppointmentSlotModel(1L, existingStartTime, existingEndTime, 2L, 1L);

        List<AppointmentSlotModel> existingSlots = Collections.singletonList(existingSlot);

        // Act & Assert
        assertThrows(TimeSlotConflictException.class, () -> timeSlotValidator.validate(newSlot, existingSlots));
    }

    @Test
    void When_Validate_And_ExistingSlotCompletelyOverlapsNewSlot_Expect_TimeSlotConflictException() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newStartTime = now.plusDays(1).plusMinutes(30);
        LocalDateTime newEndTime = newStartTime.plusHours(1);
        AppointmentSlotModel newSlot = new AppointmentSlotModel(null, newStartTime, newEndTime, 1L, 1L);

        LocalDateTime existingStartTime = now.plusDays(1);
        LocalDateTime existingEndTime = newStartTime.plusHours(2);
        AppointmentSlotModel existingSlot = new AppointmentSlotModel(1L, existingStartTime, existingEndTime, 2L, 1L);

        List<AppointmentSlotModel> existingSlots = Collections.singletonList(existingSlot);

        // Act & Assert
        assertThrows(TimeSlotConflictException.class, () -> timeSlotValidator.validate(newSlot, existingSlots));
    }

}