package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.name LIKE %:name%")
    List<Category> findByName(@Param("name") String name);
}
