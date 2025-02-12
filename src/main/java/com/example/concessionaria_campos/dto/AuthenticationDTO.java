package com.example.concessionaria_campos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AuthenticationDTO {

    @NotBlank(message = "O login do usuário é obrigatório.")
    private String login;

    @NotBlank(message = "A senha do usuário é obrigatório.")
    private String password;
}
