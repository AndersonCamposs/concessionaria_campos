package com.example.concessionaria_campos.assembler;

import com.example.concessionaria_campos.controller.CategoryController;
import com.example.concessionaria_campos.dto.CategoryDTO;
import com.example.concessionaria_campos.entity.Category;
import com.example.concessionaria_campos.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOAssembler implements RepresentationModelAssembler<Category, CategoryDTO> {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryDTO toModel(Category entity) {
        CategoryDTO categoryDTO = categoryMapper.toDTO(entity);
        categoryDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoryController.class)
                .fetchById(entity.getId())).withSelfRel());
        categoryDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoryController.class)
                .fetchAll()).withRel("all-categories"));

        return categoryDTO;
    }
}
