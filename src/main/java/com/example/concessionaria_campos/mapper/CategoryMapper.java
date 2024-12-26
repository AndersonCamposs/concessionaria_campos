package com.example.concessionaria_campos.mapper;

import com.example.concessionaria_campos.dto.CategoryDTO;
import com.example.concessionaria_campos.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel =  "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDTO dto);

    CategoryDTO toDTO(Category entity);
}
