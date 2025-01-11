package com.example.concessionaria_campos.controller;

import com.example.concessionaria_campos.assembler.CategoryDTOAssembler;
import com.example.concessionaria_campos.dto.ApiResponse;
import com.example.concessionaria_campos.dto.CategoryDTO;
import com.example.concessionaria_campos.dto.Create;
import com.example.concessionaria_campos.dto.Update;
import com.example.concessionaria_campos.mapper.CategoryMapper;
import com.example.concessionaria_campos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryDTOAssembler categoryDTOAssembler;
    @Autowired
    private CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody @Validated(Create.class) CategoryDTO data) {
        CategoryDTO savedCategory = categoryService.saveCategory(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryDTOAssembler.toModel(categoryMapper.toEntity(savedCategory)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody @Validated(Update.class) CategoryDTO data, @PathVariable Long id) {
        CategoryDTO updatedCategory = categoryService.updateCategory(data, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryDTOAssembler.toModel(categoryMapper.toEntity(updatedCategory)));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> fetchAll() {
        List<CategoryDTO> categoryList = categoryService.fetchAll()
                .stream()
                .map(categoryDTO -> categoryDTOAssembler.toModel(categoryMapper.toEntity(categoryDTO)))
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> fetchByName(
            @RequestParam(value = "name", required = true
    ) String name) {
        List<CategoryDTO> categoryList = categoryService.fetchByName(name)
                .stream()
                .map(categoryDTO -> categoryDTOAssembler.toModel(categoryMapper.toEntity(categoryDTO)))
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> fetchById(@PathVariable Long id) {
        CategoryDTO existingCategory = categoryService.fetchById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryDTOAssembler.toModel(categoryMapper.toEntity(existingCategory)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        ApiResponse response = categoryService.deleteCategory(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
