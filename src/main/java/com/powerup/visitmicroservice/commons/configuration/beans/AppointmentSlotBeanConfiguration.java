package com.powerup.visitmicroservice.commons.configuration.beans;

import com.powerup.visitmicroservice.domain.ports.in.AppointmentSlotServicePort;
import com.powerup.visitmicroservice.domain.ports.out.AppointmentSlotPersistencePort;
import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;
import com.powerup.visitmicroservice.domain.usecases.AppointmentSlotUseCase;
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
    
    @Bean
    public AppointmentSlotPersistencePort appointmentSlotPersistencePort(){
        return new AppointmentSlotPersistenceAdapter(appointmentSlotEntityMapper, appointmentSlotRepository);
    }
    
    @Bean
    public AppointmentSlotServicePort appointmentSlotServicePort(
            AppointmentSlotPersistencePort appointmentSlotPersistencePort,
            AuthenticatedUserPort authenticatedUserPort
    ){
        return new AppointmentSlotUseCase(appointmentSlotPersistencePort, authenticatedUserPort);
    }
}
