package com.powerup.visitmicroservice.domain.model;

public class VisitModel {
    
    private Long id;
    private AppointmentSlotModel appointmentSlot;
    private String customerEmail;

    public VisitModel() {
    }

    public VisitModel(Long id, AppointmentSlotModel appointmentSlot, String customerEmail) {
        this.id = id;
        this.appointmentSlot = appointmentSlot;
        this.customerEmail = customerEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppointmentSlotModel getAppointmentSlot() {
        return appointmentSlot;
    }

    public void setAppointmentSlot(AppointmentSlotModel appointmentSlot) {
        this.appointmentSlot = appointmentSlot;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
