package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
