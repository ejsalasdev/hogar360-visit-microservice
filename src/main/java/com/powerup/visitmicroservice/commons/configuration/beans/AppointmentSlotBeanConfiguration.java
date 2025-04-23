package com.powerup.visitmicroservice.commons.configuration.beans;

import com.powerup.visitmicroservice.application.client.handler.PropertyHandlerClient;
import com.powerup.visitmicroservice.domain.ports.in.AppointmentSlotServicePort;
import com.powerup.visitmicroservice.domain.ports.out.AppointmentSlotPersistencePort;
import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;
import com.powerup.visitmicroservice.domain.usecases.AppointmentSlotUseCase;
import com.powerup.visitmicroservice.domain.utils.validation.TimeSlotValidator;
import com.powerup.visitmicroservice.infrastructure.adapters.persistence.AppointmentSlotPersistenceAdapter;
import com.powerup.visitmicroservice.infrastructure.mappers.AppointmentSlotEntityMapper;
import com.powerup.visitmicroservice.infrastructure.repositories.mysql.AppointmentSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppointmentSlotBeanConfiguration {

    private final AppointmentSlotEntityMapper appointmentSlotEntityMapper;
    private final AppointmentSlotRepository appointmentSlotRepository;
    private final PropertyHandlerClient propertyHandlerClient;

    @Bean
    public AppointmentSlotPersistencePort appointmentSlotPersistencePort() {
        return new AppointmentSlotPersistenceAdapter(appointmentSlotEntityMapper, appointmentSlotRepository, propertyHandlerClient);
    }

    @Bean
    public TimeSlotValidator timeSlotValidator() {
        return new TimeSlotValidator();
    }

    @Bean
    public AppointmentSlotServicePort appointmentSlotServicePort(
            AppointmentSlotPersistencePort appointmentSlotPersistencePort,
            AuthenticatedUserPort authenticatedUserPort,
            TimeSlotValidator timeSlotValidator
    ) {
        return new AppointmentSlotUseCase(appointmentSlotPersistencePort, authenticatedUserPort, timeSlotValidator);
    }
}
