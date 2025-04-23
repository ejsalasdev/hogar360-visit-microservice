package com.powerup.visitmicroservice.domain.usecases;

import com.powerup.visitmicroservice.domain.exceptions.DuplicateVisitException;
import com.powerup.visitmicroservice.domain.exceptions.MaximumBuyersReachedException;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.model.VisitModel;
import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;
import com.powerup.visitmicroservice.domain.ports.out.VisitPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VisitUseCaseTest {

    @Mock
    private VisitPersistencePort visitPersistencePort;

    @Mock
    private AuthenticatedUserPort authenticatedUserPort;

    @InjectMocks
    private VisitUseCase visitUseCase;

    private VisitModel visitModel;
    private AppointmentSlotModel appointmentSlotModel;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        appointmentSlotModel = new AppointmentSlotModel();
        appointmentSlotModel.setId(1L);

        visitModel = new VisitModel();
        visitModel.setAppointmentSlotId(appointmentSlotModel);
        visitModel.setCustomerEmail("test@example.com");
    }

    @Test
    void When_SaveVisit_Expect_SuccessfulVisit() {
        when(authenticatedUserPort.getCurrentUsername()).thenReturn("test@example.com");
        when(visitPersistencePort.findByAppointmentSlotIdAndCustomerEmail(1L, "test@example.com"))
                .thenReturn(Optional.empty());
        when(visitPersistencePort.countByAppointmentSlotId(1L)).thenReturn(0);
        doNothing().when(visitPersistencePort).save(visitModel);

        assertDoesNotThrow(() -> visitUseCase.save(visitModel));
        verify(authenticatedUserPort, times(1)).getCurrentUsername();
        verify(visitPersistencePort, times(1)).findByAppointmentSlotIdAndCustomerEmail(1L, "test@example.com");
        verify(visitPersistencePort, times(1)).countByAppointmentSlotId(1L);
        verify(visitPersistencePort, times(1)).save(visitModel);
    }

    @Test
    void When_SaveVisit_Expect_DuplicateVisitForBuyer() {
        when(authenticatedUserPort.getCurrentUsername()).thenReturn("test@example.com");
        when(visitPersistencePort.findByAppointmentSlotIdAndCustomerEmail(1L, "test@example.com"))
                .thenReturn(Optional.of(visitModel));

        assertThrows(DuplicateVisitException.class, () -> visitUseCase.save(visitModel));
        verify(authenticatedUserPort, times(1)).getCurrentUsername();
        verify(visitPersistencePort, times(1)).findByAppointmentSlotIdAndCustomerEmail(1L, "test@example.com");
        verify(visitPersistencePort, never()).countByAppointmentSlotId(anyLong());
        verify(visitPersistencePort, never()).save(any());
    }

    @Test
    void When_SaveVisit_Expect_MaximumBuyersReached() {
        when(authenticatedUserPort.getCurrentUsername()).thenReturn("another@example.com");
        when(visitPersistencePort.findByAppointmentSlotIdAndCustomerEmail(1L, "another@example.com"))
                .thenReturn(Optional.empty());
        when(visitPersistencePort.countByAppointmentSlotId(1L)).thenReturn(2);

        assertThrows(MaximumBuyersReachedException.class, () -> visitUseCase.save(visitModel));
        verify(authenticatedUserPort, times(1)).getCurrentUsername();
        verify(visitPersistencePort, times(1)).findByAppointmentSlotIdAndCustomerEmail(1L, "another@example.com");
        verify(visitPersistencePort, times(1)).countByAppointmentSlotId(1L);
        verify(visitPersistencePort, never()).save(any());
    }

    @Test
    void When_SaveVisit_Expect_MaximumBuyersReached_WithExistingBuyer() {
        when(authenticatedUserPort.getCurrentUsername()).thenReturn("third@example.com");
        when(visitPersistencePort.findByAppointmentSlotIdAndCustomerEmail(1L, "third@example.com"))
                .thenReturn(Optional.empty());
        when(visitPersistencePort.countByAppointmentSlotId(1L)).thenReturn(1);

        VisitModel anotherVisit = new VisitModel();
        anotherVisit.setAppointmentSlotId(appointmentSlotModel);
        anotherVisit.setCustomerEmail("different@example.com");
        when(visitPersistencePort.findByAppointmentSlotIdAndCustomerEmail(1L, "different@example.com"))
                .thenReturn(Optional.of(anotherVisit));

        VisitModel thirdVisit = new VisitModel();
        thirdVisit.setAppointmentSlotId(appointmentSlotModel);
        thirdVisit.setCustomerEmail("third@example.com");

        when(visitPersistencePort.countByAppointmentSlotId(1L)).thenReturn(2);

        assertThrows(MaximumBuyersReachedException.class, () -> visitUseCase.save(thirdVisit));
        verify(authenticatedUserPort, times(1)).getCurrentUsername();
        verify(visitPersistencePort, times(1)).findByAppointmentSlotIdAndCustomerEmail(1L, "third@example.com");
        verify(visitPersistencePort, times(1)).countByAppointmentSlotId(1L);
        verify(visitPersistencePort, never()).save(any());
    }
}