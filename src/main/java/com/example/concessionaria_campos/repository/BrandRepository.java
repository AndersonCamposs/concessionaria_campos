package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("SELECT b FROM Brand b WHERE b.name LIKE %:name%")
    List<Brand> findByName(@Param("name") String name);
}
