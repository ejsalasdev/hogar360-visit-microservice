package com.powerup.visitmicroservice.infrastructure.adapters.persistence;

import com.powerup.visitmicroservice.application.client.dto.HouseInfoResponse;
import com.powerup.visitmicroservice.application.client.handler.PropertyHandlerClient;
import com.powerup.visitmicroservice.domain.model.AppointmentSlotModel;
import com.powerup.visitmicroservice.domain.ports.out.AppointmentSlotPersistencePort;
import com.powerup.visitmicroservice.domain.utils.pagination.PageInfo;
import com.powerup.visitmicroservice.infrastructure.entities.AppointmentSlotEntity;
import com.powerup.visitmicroservice.infrastructure.mappers.AppointmentSlotEntityMapper;
import com.powerup.visitmicroservice.infrastructure.repositories.mysql.AppointmentSlotRepository;
import com.powerup.visitmicroservice.infrastructure.repositories.specifications.AppointmentSlotSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AppointmentSlotPersistenceAdapter implements AppointmentSlotPersistencePort {

    private final AppointmentSlotEntityMapper appointmentSlotEntityMapper;
    private final AppointmentSlotRepository appointmentSlotRepository;
    private final PropertyHandlerClient propertyHandlerClient;

    @Override
    public void save(AppointmentSlotModel appointmentSlotModel) {
        AppointmentSlotEntity appointmentSlotEntity = appointmentSlotEntityMapper.modelToEntity(appointmentSlotModel);
        appointmentSlotRepository.save(appointmentSlotEntity);
    }

    @Override
    public List<AppointmentSlotModel> getAllByHouseId(Long houseId) {
        List<AppointmentSlotEntity> appointmentSlotEntities = appointmentSlotRepository.findAllByHouseId(houseId);
        return appointmentSlotEntities.stream()
                .map(appointmentSlotEntityMapper::entityToModel)
                .toList();
    }

    @Override
    public Optional<AppointmentSlotModel> getAllBySellerIdAndStarTime(Long sellerId, LocalDateTime startTime) {
        return appointmentSlotRepository.findAllBySellerIdAndStartTime(sellerId, startTime).map(appointmentSlotEntityMapper::entityToModel);
    }

    @Override
    public PageInfo<AppointmentSlotModel> getdAllAvailable(
            Integer page, Integer size, LocalDateTime startTime, LocalDateTime endTime, String city
    ) {

        Sort sort = Sort.by(Sort.Direction.DESC, "startTime");
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<AppointmentSlotEntity> spec = Specification
                .where(AppointmentSlotSpecification.filterByStartTimeGreaterThanOrEqual(startTime))
                .and(AppointmentSlotSpecification.filterByEndTimeLessThanOrEqual(endTime))
                .and(AppointmentSlotSpecification.isAvailable())
                .and(Specification.not(AppointmentSlotSpecification.excludePastAndFull()));

        Page<AppointmentSlotEntity> appointmentSlotEntitiesPage = appointmentSlotRepository.findAll(spec, pageable);

        List<AppointmentSlotModel> filteredContent = appointmentSlotEntitiesPage.getContent().stream()
                .filter(entity -> {
                    try {
                        HouseInfoResponse houseInfo = propertyHandlerClient.getHouseInfoById(entity.getHouseId());
                        return houseInfo != null && houseInfo.city() != null &&
                                (city == null || city.isEmpty() || houseInfo.city().equalsIgnoreCase(city));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .map(appointmentSlotEntityMapper::entityToModel)
                .toList();

        long totalElements = appointmentSlotRepository.findAll(spec).stream()
                .filter(entity -> {
                    try {
                        HouseInfoResponse houseInfo = propertyHandlerClient.getHouseInfoById(entity.getHouseId());
                        return houseInfo != null && houseInfo.city() != null &&
                                (city == null || city.isEmpty() || houseInfo.city().equalsIgnoreCase(city));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }).count();

        return new PageInfo<>(
                filteredContent,
                totalElements,
                (int) Math.ceil((double) totalElements / size),
                page,
                size,
                (page + 1) * size < totalElements,
                page > 0
        );
    }
}
