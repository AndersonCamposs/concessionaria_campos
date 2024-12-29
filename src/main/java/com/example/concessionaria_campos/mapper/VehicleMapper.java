package com.example.concessionaria_campos.mapper;

import com.example.concessionaria_campos.dto.VehicleDTO;
import com.example.concessionaria_campos.entity.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BrandMapper.class, CategoryMapper.class, PhotoMapper.class})
public interface VehicleMapper {

    Vehicle toEntity(VehicleDTO dto);

    VehicleDTO toDTO(Vehicle entity);
}
