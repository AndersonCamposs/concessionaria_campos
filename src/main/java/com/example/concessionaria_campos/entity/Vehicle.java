package com.example.concessionaria_campos.entity;

import com.example.concessionaria_campos.enums.TransmissionType;
import com.example.concessionaria_campos.enums.VehicleStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "plate", nullable = false)
    private String plate;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "year", nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "transmission_type", nullable = false)
    private TransmissionType transmissionType;

    @Column(name = "status", nullable = false)
    private VehicleStatus status;
}
