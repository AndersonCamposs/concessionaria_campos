package com.example.concessionaria_campos.entity;

import com.example.concessionaria_campos.enums.TransmissionType;
import com.example.concessionaria_campos.enums.VehicleStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "chassis_number", unique = true)
    private String chassisNumber;

    @Column(name = "plate", nullable = false)
    private String plate;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "year", nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @Column(name = "transmission_type", nullable = false, columnDefinition = "enum('AUTOMATIC','MANUAL')")
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    @Column(name = "status", nullable = false, columnDefinition = "enum('AVAILABLE','MAINTENANCE','SOLD')")
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "odometer_value", nullable = true)
    private Integer odometerValue;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Photo> photos;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.REMOVE)
    private Sale sale;
}
