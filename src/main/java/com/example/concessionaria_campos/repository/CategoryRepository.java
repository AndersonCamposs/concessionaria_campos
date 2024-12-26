package com.example.concessionaria_campos.repository;

import com.example.concessionaria_campos.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
