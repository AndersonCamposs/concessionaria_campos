package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
