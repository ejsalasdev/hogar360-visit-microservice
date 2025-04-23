package com.powerup.visitmicroservice.domain.usecases;

import com.powerup.visitmicroservice.domain.exceptions.TimeSlotConflictException;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.ports.out.AppointmentSlotPersistencePort;
import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;
import com.powerup.visitmicroservice.domain.utils.pagination.PageInfo;
import com.powerup.visitmicroservice.domain.utils.validation.TimeSlotValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentSlotUseCaseTest {

    @Mock
    AppointmentSlotPersistencePort appointmentSlotPersistencePort;
    @Mock
    AuthenticatedUserPort authenticatedUserPort;
    @Mock
    TimeSlotValidator timeSlotValidator;
    @InjectMocks
    AppointmentSlotUseCase appointmentSlotUseCase;

    private Long sellerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long houseId;
    private AppointmentSlotModel appointmentSlotModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sellerId = 1L;
        houseId = 10L;
        startTime = LocalDateTime.now().plusHours(1);
        endTime = LocalDateTime.now().plusHours(2);
        appointmentSlotModel = new AppointmentSlotModel(null, startTime, endTime, sellerId, houseId);

        when(authenticatedUserPort.getCurrentUserId()).thenReturn(sellerId);
    }

    @Test
    void When_SaveAppointmentSlot_Expect_SuccessfulCreation() {
        // Arrange
        when(appointmentSlotPersistencePort.getAllBySellerIdAndStarTime(sellerId, startTime)).thenReturn(Optional.empty());
        when(appointmentSlotPersistencePort.getAllByHouseId(houseId)).thenReturn(Collections.emptyList());
        doNothing().when(timeSlotValidator).validate(appointmentSlotModel, Collections.emptyList());
        doNothing().when(appointmentSlotPersistencePort).save(appointmentSlotModel);

        // Act
        appointmentSlotUseCase.save(appointmentSlotModel);

        // Assert
        verify(authenticatedUserPort, times(1)).getCurrentUserId();
        verify(appointmentSlotPersistencePort, times(1)).getAllBySellerIdAndStarTime(sellerId, startTime);
        verify(appointmentSlotPersistencePort, times(1)).getAllByHouseId(houseId);
        verify(timeSlotValidator, times(1)).validate(appointmentSlotModel, Collections.emptyList());
        verify(appointmentSlotPersistencePort, times(1)).save(appointmentSlotModel);
    }

    @Test
    void When_SaveAppointmentSlot_And_SameTimeSlotExistsForSeller_Expect_TimeSlotConflictException() {
        // Arrange
        AppointmentSlotModel existingSlot = new AppointmentSlotModel(2L, startTime, endTime.plusHours(1), sellerId, houseId);
        when(appointmentSlotPersistencePort.getAllBySellerIdAndStarTime(sellerId, startTime)).thenReturn(Optional.of(existingSlot));

        // Act & Assert
        assertThrows(TimeSlotConflictException.class, () -> appointmentSlotUseCase.save(appointmentSlotModel));

        // Assert
        verify(authenticatedUserPort, times(1)).getCurrentUserId();
        verify(appointmentSlotPersistencePort, times(1)).getAllBySellerIdAndStarTime(sellerId, startTime);
        verify(appointmentSlotPersistencePort, never()).getAllByHouseId(anyLong());
        verify(timeSlotValidator, never()).validate(any(), anyList());
        verify(appointmentSlotPersistencePort, never()).save(any());
    }

    @Test
    void When_SaveAppointmentSlot_And_TimeSlotValidatorThrowsConflict_Expect_TimeSlotConflictException() {
        // Arrange
        List<AppointmentSlotModel> conflictingSlots = Collections.singletonList(
                new AppointmentSlotModel(2L, startTime, startTime.plusMinutes(30), 2L, houseId)
        );
        when(appointmentSlotPersistencePort.getAllBySellerIdAndStarTime(sellerId, startTime)).thenReturn(Optional.empty());
        when(appointmentSlotPersistencePort.getAllByHouseId(houseId)).thenReturn(conflictingSlots);
        doThrow(new TimeSlotConflictException("Time slot conflict")).when(timeSlotValidator).validate(appointmentSlotModel, conflictingSlots);
        doNothing().when(appointmentSlotPersistencePort).save(appointmentSlotModel);

        // Act & Assert
        assertThrows(TimeSlotConflictException.class, () -> appointmentSlotUseCase.save(appointmentSlotModel));

        // Assert
        verify(authenticatedUserPort, times(1)).getCurrentUserId();
        verify(appointmentSlotPersistencePort, times(1)).getAllBySellerIdAndStarTime(sellerId, startTime);
        verify(appointmentSlotPersistencePort, times(1)).getAllByHouseId(houseId);
        verify(timeSlotValidator, times(1)).validate(appointmentSlotModel, conflictingSlots);
        verify(appointmentSlotPersistencePort, never()).save(any());
    }

    @Test
    void When_getdAllAvailable_Expect_CallToPersistencePort() {
        // Arrange (Given)
        Integer page = 0;
        Integer size = 10;
        LocalDateTime filterStartTime = startTime.minusHours(1);
        LocalDateTime filterEndTime = endTime.plusHours(1);
        String city = "Bogotá";
        PageInfo<AppointmentSlotModel> expectedPageInfo = new PageInfo<>(
                Collections.emptyList(), 0L, 0, 0, size, false, false
        );

        when(appointmentSlotPersistencePort.getdAllAvailable(page, size, filterStartTime, filterEndTime, city))
                .thenReturn(expectedPageInfo);

        // Act (When)
        PageInfo<AppointmentSlotModel> actualPageInfo = appointmentSlotUseCase.getdAllAvailable(page, size, filterStartTime, filterEndTime, city);

        // Assert (Then)
        assertEquals(expectedPageInfo, actualPageInfo);
        verify(appointmentSlotPersistencePort, times(1)).getdAllAvailable(page, size, filterStartTime, filterEndTime, city);
    }
}