package com.powerup.visitmicroservice.domain.model;

public class VisitModel {
    
    private Long id;
    private AppointmentSlotModel appointmentSlotId;
    private String customerEmail;

    public VisitModel() {
    }

    public VisitModel(Long id, AppointmentSlotModel appointmentSlotId, String customerEmail) {
        this.id = id;
        this.appointmentSlotId = appointmentSlotId;
        this.customerEmail = customerEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppointmentSlotModel getAppointmentSlotId() {
        return appointmentSlotId;
    }

    public void setAppointmentSlotId(AppointmentSlotModel appointmentSlotId) {
        this.appointmentSlotId = appointmentSlotId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
