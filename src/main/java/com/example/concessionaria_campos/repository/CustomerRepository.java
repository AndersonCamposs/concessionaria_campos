package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Customer;
import org.hibernate.cache.spi.support.CacheUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:name%")
    List<Customer> findByName(@Param("name") String name);

    @Query("SELECT c FROM Customer c WHERE c.cpf = :cpf")
    Optional<Customer> findByCpf(@Param("cpf") String cpf);
}
