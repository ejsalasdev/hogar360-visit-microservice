package com.powerup.visitmicroservice.domain.usecases;

import com.powerup.visitmicroservice.domain.exceptions.DuplicateVisitException;
import com.powerup.visitmicroservice.domain.exceptions.MaximumBuyersReachedException;
import com.powerup.visitmicroservice.domain.model.VisitModel;
import com.powerup.visitmicroservice.domain.ports.in.VisitServicePort;
import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;
import com.powerup.visitmicroservice.domain.ports.out.VisitPersistencePort;
import com.powerup.visitmicroservice.domain.utils.constants.DomainConstants;
import com.powerup.visitmicroservice.domain.utils.constants.VisitExceptionMessagesConstants;

import java.util.Optional;

public class VisitUseCase implements VisitServicePort {
    
    private final VisitPersistencePort visitPersistencePort;
    private final AuthenticatedUserPort authenticatedUserPort;

    public VisitUseCase(VisitPersistencePort visitPersistencePort, AuthenticatedUserPort authenticatedUserPort) {
        this.visitPersistencePort = visitPersistencePort;
        this.authenticatedUserPort = authenticatedUserPort;
    }

    @Override
    public void save(VisitModel visitModel) {
        visitModel.setCustomerEmail(authenticatedUserPort.getCurrentUsername());
        
        Optional<VisitModel> existingVisitForBuyer = visitPersistencePort.findByAppointmentSlotIdAndCustomerEmail(
                visitModel.getAppointmentSlotId().getId(), visitModel.getCustomerEmail()
        );
        
        if (existingVisitForBuyer.isPresent()) {
            throw new DuplicateVisitException(VisitExceptionMessagesConstants.VISIT_ALREADY_EXIST_FOR_BUYER);
        }
        
        int existingVisitsCount = visitPersistencePort.countByAppointmentSlotId(visitModel.getAppointmentSlotId().getId());
        
        if (existingVisitsCount >= DomainConstants.VISIT_LIMIT_FOR_APPOINTMENT_SLOT) {
            throw new MaximumBuyersReachedException(
                    VisitExceptionMessagesConstants.VISIT_LIMIT_REACHED_ERROR
            );
        }
        
        visitPersistencePort.save(visitModel);
    }
}
