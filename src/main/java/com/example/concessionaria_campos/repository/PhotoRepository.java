package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Photo;
import com.example.concessionaria_campos.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("SELECT p FROM Photo p WHERE p.vehicle = :vehicle")
    List<Photo> findByVehicle(@Param("vehicle") Vehicle vehicle);
}
