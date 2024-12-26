package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.CategoryDTO;
import com.example.concessionaria_campos.entity.Category;
import com.example.concessionaria_campos.exception.ResourceNotFoundException;
import com.example.concessionaria_campos.mapper.CategoryMapper;
import com.example.concessionaria_campos.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMapper categoryMapper;

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
        BeanUtils.copyProperties(category, existingCategory);
        existingCategory.setId(id);
        Category updatedCategory = categoryRepository.save(existingCategory);

        return categoryMapper.toDTO(updatedCategory);
    }

    public List<CategoryDTO> fetchAll() {
        List<CategoryDTO> categoryList = new ArrayList<>();
        categoryRepository.findAll()
                .forEach(category -> categoryList.add(categoryMapper.toDTO(category)));

        return categoryList;
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
