package com.powerup.visitmicroservice.domain.model;

import java.time.LocalDateTime;

public class AppointmentSlotModel {
    
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long sellerId;
    private Long houseId;

    public AppointmentSlotModel() {
    }

    public AppointmentSlotModel(Long id, LocalDateTime startTime, LocalDateTime endTime, Long sellerId, Long houseId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sellerId = sellerId;
        this.houseId = houseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
}
