package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
