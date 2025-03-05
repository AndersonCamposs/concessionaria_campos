package com.example.concessionaria_campos.dto;

import com.example.concessionaria_campos.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Getter
@Setter
public class UserDTO extends RepresentationModel<SaleDTO> {

    private UUID id;

    private String login;

    @JsonIgnore
    private String password;

    private UserRole role;
}
