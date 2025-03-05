package com.example.concessionaria_campos.assembler;

import com.example.concessionaria_campos.controller.UserController;
import com.example.concessionaria_campos.dto.UserDTO;
import com.example.concessionaria_campos.entity.User;
import com.example.concessionaria_campos.mapper.UserMapper;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserDTOAssembler implements RepresentationModelAssembler<User, UserDTO> {

    private final UserMapper userMapper;

    public UserDTOAssembler(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO toModel(User entity) {
        UserDTO userDTO = userMapper.toDTO(entity);

        userDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .fetchById(entity.getId())).withSelfRel());

        return userDTO;
    }
}
