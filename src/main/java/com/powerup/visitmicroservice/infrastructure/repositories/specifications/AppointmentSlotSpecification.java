package com.powerup.visitmicroservice.infrastructure.repositories.specifications;

import com.powerup.visitmicroservice.infrastructure.entities.AppointmentSlotEntity;
import com.powerup.visitmicroservice.infrastructure.entities.VisitEntity;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class AppointmentSlotSpecification {

    public static Specification<AppointmentSlotEntity> filterByStartTimeGreaterThanOrEqual(LocalDateTime startTime) {
        return (root, query, criteriaBuilder) ->
                startTime != null ? criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), startTime) : criteriaBuilder.conjunction();
    }

    public static Specification<AppointmentSlotEntity> filterByEndTimeLessThanOrEqual(LocalDateTime endTime) {
        return (root, query, criteriaBuilder) ->
                endTime != null ? criteriaBuilder.lessThanOrEqualTo(root.get("startTime"), endTime) : criteriaBuilder.conjunction();
    }

    public static Specification<AppointmentSlotEntity> isAvailable() {
        return (root, query, criteriaBuilder) -> {
            assert query != null;
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<VisitEntity> visitRoot = subquery.from(VisitEntity.class);
            subquery.select(criteriaBuilder.count(visitRoot))
                    .where(criteriaBuilder.equal(visitRoot.get("appointmentSlotId"), root));
            return criteriaBuilder.lessThan(subquery, 2L);
        };
    }

    public static Specification<AppointmentSlotEntity> isFuture() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), LocalDateTime.now(java.time.Clock.system(java.time.ZoneId.of("America/Bogota"))));
    }

    public static Specification<AppointmentSlotEntity> excludePastAndFull() {
        Specification<AppointmentSlotEntity> isPast = (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("startTime"), LocalDateTime.now(java.time.Clock.system(java.time.ZoneId.of("America/Bogota"))));

        Specification<AppointmentSlotEntity> isFull = (root, query, criteriaBuilder) -> {
            assert query != null;
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<VisitEntity> visitRoot = subquery.from(VisitEntity.class);
            subquery.select(criteriaBuilder.count(visitRoot))
                    .where(criteriaBuilder.equal(visitRoot.get("appointmentSlotId"), root));
            return criteriaBuilder.greaterThanOrEqualTo(subquery, 2L);
        };

        return isPast.and(isFull);
    }

}
