package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s WHERE s.customer.id = :id")
    List<Sale> findByUserId(@Param("id") Long id);
}
