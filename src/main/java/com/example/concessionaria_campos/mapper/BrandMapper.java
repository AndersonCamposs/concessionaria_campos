package com.example.concessionaria_campos.mapper;

import com.example.concessionaria_campos.dto.BrandDTO;
import com.example.concessionaria_campos.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toEntity(BrandDTO dto);

    BrandDTO toDTO(Brand entity);
}
