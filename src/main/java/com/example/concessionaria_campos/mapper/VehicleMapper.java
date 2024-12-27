package com.example.concessionaria_campos.mapper;

import com.example.concessionaria_campos.dto.VehicleDTO;
import com.example.concessionaria_campos.entity.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toEntity(VehicleDTO dto);

    VehicleDTO toDTO(Vehicle entity);
}
