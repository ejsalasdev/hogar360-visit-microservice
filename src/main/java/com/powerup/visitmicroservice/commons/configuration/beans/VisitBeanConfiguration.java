package com.powerup.visitmicroservice.commons.configuration.beans;

import com.powerup.visitmicroservice.domain.ports.in.VisitServicePort;
import com.powerup.visitmicroservice.domain.ports.out.AuthenticatedUserPort;
import com.powerup.visitmicroservice.domain.ports.out.VisitPersistencePort;
import com.powerup.visitmicroservice.domain.usecases.VisitUseCase;
import com.powerup.visitmicroservice.infrastructure.adapters.persistence.VisitPersistenceAdapter;
import com.powerup.visitmicroservice.infrastructure.mappers.VisitEntityMapper;
import com.powerup.visitmicroservice.infrastructure.repositories.mysql.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class VisitBeanConfiguration {
    
    private final VisitEntityMapper visitEntityMapper;
    private final VisitRepository visitRepository;
    
    @Bean
    public VisitPersistencePort visitPersistencePort() {
        return new VisitPersistenceAdapter(visitEntityMapper, visitRepository);
    }
    
    @Bean
    public VisitServicePort visitServicePort(
            VisitPersistencePort visitPersistencePort,
            AuthenticatedUserPort authenticatedUserPort
    ) {
        return new VisitUseCase(visitPersistencePort, authenticatedUserPort);
    }
}
