package com.powerup.visitmicroservice.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visits")
public class VisitEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "appointment_slot_id", nullable = false)
    private AppointmentSlotEntity appointmentSlot;
    
    @Column(nullable = false)
    private String customerEmail;
}
