package com.example.concessionaria_campos.mapper;

import com.example.concessionaria_campos.dto.PhotoDTO;
import com.example.concessionaria_campos.entity.Photo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    Photo toEntity(PhotoDTO dto);

    PhotoDTO toDTO(Photo entity);
}
