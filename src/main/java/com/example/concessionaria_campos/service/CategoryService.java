package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.CategoryDTO;
import com.example.concessionaria_campos.entity.Category;
import com.example.concessionaria_campos.entity.Vehicle;
import com.example.concessionaria_campos.exception.ResourceNotFoundException;
import com.example.concessionaria_campos.mapper.CategoryMapper;
import com.example.concessionaria_campos.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryDTO saveCategory(CategoryDTO category) {
        try {
            Category savedCategory = categoryRepository.save(categoryMapper.toEntity(category));
            return categoryMapper.toDTO(savedCategory);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar os dados da categoria");
        }
    }

    public CategoryDTO updateCategory(CategoryDTO category, Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        try {
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            Category updatedCategory = categoryRepository.save(existingCategory);

            return categoryMapper.toDTO(updatedCategory);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar dados da categoria\n" + e.getMessage());
        }

    }

    public List<CategoryDTO> fetchAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    public List<CategoryDTO> fetchByName(String name) {
        return categoryRepository.findByName(name)
                .stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    public CategoryDTO fetchById(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        return categoryMapper.toDTO(existingCategory);
    }

    public ApiResponse deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        categoryRepository.delete(existingCategory);
        return new ApiResponse("Categoria deletada com sucesso");
    }
}
