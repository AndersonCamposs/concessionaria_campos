package com.example.concessionaria_campos.mapper;

import com.example.concessionaria_campos.dto.MarcaDTO;
import com.example.concessionaria_campos.entity.Marca;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcaMapper {

    Marca toEntity(MarcaDTO dto);

    MarcaDTO toDTO(Marca entity);
}
