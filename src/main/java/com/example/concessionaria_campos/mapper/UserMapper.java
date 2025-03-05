package com.example.concessionaria_campos.mapper;

import com.example.concessionaria_campos.dto.UserDTO;
import com.example.concessionaria_campos.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDTO(User user);
}
