package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
