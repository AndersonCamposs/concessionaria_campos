package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
