package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
