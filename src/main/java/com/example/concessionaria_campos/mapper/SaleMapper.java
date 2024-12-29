package com.example.concessionaria_campos.mapper;

import com.example.concessionaria_campos.dto.SaleDTO;
import com.example.concessionaria_campos.dto.VehicleDTO;
import com.example.concessionaria_campos.entity.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class, CustomerMapper.class})
public interface SaleMapper {

    Sale toEntity(SaleDTO dto);

    SaleDTO toDTO(Sale entity);
}
